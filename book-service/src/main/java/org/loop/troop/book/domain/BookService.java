package org.loop.troop.book.domain;

import org.loop.troop.book.domain.modal.BookDto;
import org.loop.troop.book.domain.service.Vendor;

/**
 * The interface Book service.
 *
 * @author alex
 */
public interface BookService {

	/**
	 * Register book dto.
	 * @param url the url
	 * @param vendor the vendor
	 * @return the book dto
	 */
	BookDto register(String url, Vendor vendor);

}
