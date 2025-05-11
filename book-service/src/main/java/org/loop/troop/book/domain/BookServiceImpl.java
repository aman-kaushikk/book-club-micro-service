package org.loop.troop.book.domain;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.loop.troop.book.domain.enums.BookStatus;
import org.loop.troop.book.domain.enums.Vendor;
import org.loop.troop.book.domain.modal.BookDto;
import org.loop.troop.book.domain.modal.BuyLinkDto;
import org.loop.troop.book.domain.modal.PageDto;
import org.loop.troop.book.domain.modal.RowEffected;
import org.loop.troop.book.domain.request.book.BookUpdateRequest;
import org.loop.troop.book.domain.request.buylinks.AddBuyLinkRequest;
import org.loop.troop.book.domain.request.buylinks.RemoveBuyLinkRequest;
import org.loop.troop.book.domain.request.club.AddClubIdRequest;
import org.loop.troop.book.domain.request.club.RemoveClubIdRequest;
import org.loop.troop.book.domain.request.genre.AddGenreRequest;
import org.loop.troop.book.domain.request.genre.RemoveGenreRequest;
import org.loop.troop.book.domain.request.tag.AddTagRequest;
import org.loop.troop.book.domain.request.tag.RemoveTagRequest;
import org.loop.troop.book.domain.service.BookScraperService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.*;

import static org.loop.troop.book.domain.Utility.getPageable;

/**
 * The type Book service.
 */
@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class BookServiceImpl implements BookService {

	private final List<BookScraperService> scraperServices;

	private final BookMapper bookMapper;

	private final BookRepository bookRepository;

	private final PageMapper<Book, BookDto> pageMapper;

	@Override
	public void updateBook(BookUpdateRequest bookUpdateRequest) {
		var book  = bookRepository.findById(bookUpdateRequest.getBookId()).orElseThrow( () -> new ServiceException("Book found with given title: " + bookUpdateRequest.getBookId()));
		updateBookFromRequest(book,bookUpdateRequest);
		book.setUpdatedAt(LocalDateTime.now());
		bookRepository.save(book);
	}

	@Override
	public boolean isBookPresent(String url) {
		return bookRepository.existsByUrl(url);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public BookDto register(String url, Vendor vendor) {
		Assert.notNull(url, "URL is mandatory");
		Assert.notNull(url, "vendor is mandatory");
		for (BookScraperService service : scraperServices) {
			if (service.support(vendor)) {
				BookDto bookDto = service.validateAndRegister(url);
				log.debug("Book dto: {}", bookDto);
				Book book = bookMapper.toBookEntity(bookDto);
				book.setBookmarked(0);
				if (bookRepository.existsByTitle(book.getTitle())) {
					throw new ServiceException("Book found with given title: " + book.getTitle());
				}
				book = bookRepository.save(book);
				bookDto.setBookId(book.getBookId());
				return bookDto;
			}
		}
		String message = String.format("No Support fo Current vendor: %s", vendor.name());
		throw new ServiceException(message);
	}

	@Override
	public List<BuyLinkDto> getAllBuyLink() {
		return bookMapper.toDtoBuyLinkList(bookRepository.getAllBuyLink());
	}

	@Override
	@Cacheable(value = "books", key = "#page + '-' + #size + '-' + #sortBy + '-' + #sortDirection")
	public PageDto<BookDto> getAllBook(int page, int size, String sortBy, String sortDirection) {
		Assert.notNull(sortBy, "sorting field is mandatory");
		Pageable pageable = getPageable(page, size, sortBy, sortDirection);
		Page<Book> bookPages = bookRepository.findAll(pageable);
		return pageMapper.convertToDto(bookPages, bookMapper::toDtoBookList);
	}

	// -------------------------------------- Additional methods
	// -------------------------------------------------------------

	@Override
	public RowEffected addBuyLinks(UUID bookId, AddBuyLinkRequest request) {
		Book book = getBook(bookId);
		Set<BuyLink> savedBuyLinksSet = new HashSet<>(book.getBuyLinks());
		List<BuyLink> requestedBuyLinks = bookMapper.toEntityBuyLinkList(request.getBuyLinks());
		return addOrRemoveCollectionItems(book, requestedBuyLinks, savedBuyLinksSet, "add", book.getBuyLinks(),
				"buy-link");
	}

	@Override
	public RowEffected removeBuyLinks(UUID bookId, RemoveBuyLinkRequest request) {
		Book book = getBook(bookId);
		Set<BuyLink> savedBuyLinksSet = new HashSet<>(book.getBuyLinks());
		List<BuyLink> requestedBuyLinks = bookMapper.toEntityBuyLinkList(request.getBuyLinks());
		return addOrRemoveCollectionItems(book, requestedBuyLinks, savedBuyLinksSet, "remove", book.getBuyLinks(),
				"buy-link");
	}

	@Override
	public RowEffected addClubs(UUID bookId, AddClubIdRequest request) {
		Book book = getBook(bookId);
		Set<UUID> savedClubIdSet = new HashSet<>(book.getClubIds());
		List<UUID> requestedClubIds = request.getClubIds();
		return addOrRemoveCollectionItems(book, requestedClubIds, savedClubIdSet, "add", book.getClubIds(), "club");
	}

	@Override
	public RowEffected removeClubs(UUID bookId, RemoveClubIdRequest request) {
		Book book = getBook(bookId);
		Set<UUID> savedClubIdSet = new HashSet<>(book.getClubIds());
		List<UUID> requestedClubIds = request.getClubIds();
		return addOrRemoveCollectionItems(book, requestedClubIds, savedClubIdSet, "remove", book.getClubIds(), "club");
	}

	@Override
	public RowEffected addGenres(UUID bookId, AddGenreRequest request) {
		Book book = getBook(bookId);
		Set<String> savedGenresSet = new HashSet<>(book.getGenres());
		return addOrRemoveCollectionItems(book, request.getGenres(), savedGenresSet, "add", book.getGenres(), "genre");
	}

	@Override
	public RowEffected removeGenres(UUID bookId, RemoveGenreRequest request) {
		Book book = getBook(bookId);
		Set<String> savedGenresSet = new HashSet<>(book.getGenres());
		return addOrRemoveCollectionItems(book, request.getGenres(), savedGenresSet, "remove", book.getGenres(),
				"genre");
	}

	@Override
	public RowEffected addTags(UUID bookId, AddTagRequest request) {
		Book book = getBook(bookId);
		Set<String> savedTagsSet = new HashSet<>(book.getTags());
		return addOrRemoveCollectionItems(book, request.getTags(), savedTagsSet, "add", book.getTags(), "tag");
	}

	@Override
	public RowEffected removeTags(UUID bookId, RemoveTagRequest request) {
		Book book = getBook(bookId);
		Set<String> savedTagsSet = new HashSet<>(book.getTags());
		return addOrRemoveCollectionItems(book, request.getTags(), savedTagsSet, "remove", book.getTags(), "tag");
	}

	private Book getBook(UUID bookId) {
		return bookRepository.findById(bookId)
			.orElseThrow(() -> new ServiceException("Book not found with given ID: " + bookId));
	}

	private RowEffected rowEffected(List<Object> rowDataList, String key) {
		RowEffected rowEffected = new RowEffected();
		Map<String, Object> rows = new HashMap<>();
		for (int i = 0; i < rowDataList.size(); i++) {
			String currentKey = key + "-" + (i + 1);
			rows.put(currentKey, rowDataList.get(i));
		}
		int rowCount = rowDataList.size();
		rowEffected.setRowCount(rowCount);
		rowEffected.setRows(rows);
		return rowEffected;
	}

	private <T> RowEffected addOrRemoveCollectionItems(Book book, List<T> requestedItems, Set<T> savedItemsSet,
			String operationType, Collection<T> collection, String operationField) {
		List<T> validRequestedItems = requestedItems.stream()
			.filter(item -> "add".equals(operationType) != savedItemsSet.contains(item))
			.toList();

		if ("add".equals(operationType)) {
			collection.addAll(validRequestedItems);
		}
		else if ("remove".equals(operationType)) {
			collection.removeAll(validRequestedItems);
		}

		// Save the changes
		bookRepository.save(book);

		// Return a custom response
		return rowEffected(Collections.singletonList(validRequestedItems), operationType + "-item-" + operationField);
	}

	private void updateBookFromRequest(Book book, BookUpdateRequest request) {
		if (request.getDescription() != null) {
			book.setDescription(request.getDescription());
		}

		if (request.getPageCount() != null) {
			book.setPageCount(request.getPageCount());
		}

		if (request.getRating() != null) {
			book.setRating(request.getRating());
		}

		if (request.getBookmarked() != null) {
			book.setBookmarked(request.getBookmarked());
		}

		if (request.getBookStatus() != null) {
			book.setBookStatus(BookStatus.valueOf(request.getBookStatus()));
		}
	}
}
