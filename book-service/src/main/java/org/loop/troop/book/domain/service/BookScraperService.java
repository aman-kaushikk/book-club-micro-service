package org.loop.troop.book.domain.service;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.loop.troop.book.domain.UrlScrapingException;
import org.loop.troop.book.domain.modal.BaseDto;
import org.loop.troop.book.domain.modal.BookDto;

import java.util.Set;

/**
 * The interface Book scraper service.
 *
 * @author alex
 */
@Slf4j
public abstract class BookScraperService {

	private final Validator validator;

	/**
	 * The Vendor.
	 */
	protected final Vendor vendor;

	/**
	 * Instantiates a new Book scraper service.
	 *
	 * @param validator the validator
	 * @param vendor    the vendor
	 */
	public BookScraperService(Validator validator, Vendor vendor) {
		assert validator != null;
		assert vendor != null;
		this.validator = validator;
		this.vendor = vendor;
	}

	/**
	 * Call this method to register a book after validation. This method validates the
	 * scraping URL and only proceeds if the validation passes.
	 *
	 * @param url the URL to scrape
	 * @return the registered BookDto object
	 * @throws UrlScrapingException if the validation fails
	 */
	public final BookDto validateAndRegister(String url) {
		BookDto bookDto = register(url);
		if (!validateScrapingUrl(bookDto)) {
			throw new UrlScrapingException("URL validation failed for: " + url);
		}
		return bookDto;
	}

	/**
	 * Internal method that performs the actual registration. This should be implemented
	 * by the subclass with the actual scraping logic.
	 *
	 * @param url the URL to scrape
	 * @return the scraped BookDto object
	 */
	protected abstract BookDto register(String url);

	/**
	 * Method to validate the BookDto object. You can customize this validation logic
	 * based on your requirements.
	 *
	 * @param bookDto the BookDto to validate
	 * @return true if valid, false otherwise
	 */
	protected final boolean validateScrapingUrl(BookDto bookDto) {
		boolean isValid = true;
		Set<ConstraintViolation<BookDto>> violations = validator.validate(bookDto);
		if (!violations.isEmpty()) {
			// Log or process violations if needed
			violations.forEach(violation -> log.error("Field violations message : {}", violation.getMessage()));
			isValid = false;
		}
		return isValid;
	}

	/**
	 * Support boolean.
	 *
	 * @param vendor the vendor
	 * @return the boolean
	 */
	public abstract boolean support(Vendor vendor);

}
