package org.loop.troop.book.domain;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.loop.troop.book.domain.modal.BookDto;
import org.loop.troop.book.domain.modal.BuyLinkDto;
import org.loop.troop.book.domain.modal.PageDto;
import org.loop.troop.book.domain.service.BookScraperService;
import org.loop.troop.book.domain.enums.Vendor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

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

}
