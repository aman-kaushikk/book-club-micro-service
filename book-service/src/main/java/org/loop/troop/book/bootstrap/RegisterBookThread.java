package org.loop.troop.book.bootstrap;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.loop.troop.book.domain.BookService;
import org.loop.troop.book.domain.modal.BookRequest;
import org.loop.troop.book.domain.service.Vendor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * The type Register book thread.
 */
@Component
@RequiredArgsConstructor
@Slf4j
class RegisterBookThread {

	private final BookService bookService;

	/**
	 * Register book.
	 * @param request the request
	 */
	@Async
	void registerBook(BookRequest request) {
		log.info("THREAD NAME : {}", Thread.currentThread().getName());
		try {
			bookService.register(request.getUrl(), Vendor.valueOf(request.getVendor()));
			log.info("Registered book from URL: {}", request.getUrl());
		}
		catch (Exception e) {
			log.error("Failed to register book: {} | Error: {}", request.getUrl(), e.getMessage());
		}
	}

}
