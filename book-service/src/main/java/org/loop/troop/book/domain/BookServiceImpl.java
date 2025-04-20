package org.loop.troop.book.domain;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.loop.troop.book.domain.modal.BookDto;
import org.loop.troop.book.domain.modal.BuyLinkDto;
import org.loop.troop.book.domain.service.Vendor;
import org.loop.troop.book.domain.service.BookScraperService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

	/** {@inheritDoc} */
	@Override
	public BookDto register(String url, Vendor vendor) {
		for (BookScraperService service : scraperServices) {
			if (service.support(vendor)) {
				BookDto bookDto = service.validateAndRegister(url);
				log.debug("Book dto: {}", bookDto);
				Book book = bookMapper.toEntity(bookDto);
				if(bookRepository.existsByTitle(book.getTitle())){
					throw new ServiceException("Book found with given title: "+book.getTitle());
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

}
