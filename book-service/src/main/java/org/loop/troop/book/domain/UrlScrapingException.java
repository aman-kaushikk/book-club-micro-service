package org.loop.troop.book.domain;

/**
 * The type Url scraping exception.
 */
public class UrlScrapingException extends RuntimeException {

	/**
	 * Instantiates a new Url scraping exception.
	 * @param message the message
	 */
	public UrlScrapingException(String message) {
		super(message);
	}

	/**
	 * Instantiates a new Url scraping exception.
	 * @param message the message
	 * @param cause the cause
	 */
	public UrlScrapingException(String message, Throwable cause) {
		super(message, cause);
	}

}