package org.loop.troop.book.domain;

import lombok.extern.slf4j.Slf4j;
import org.loop.troop.book.domain.modal.BookDto;
import org.loop.troop.book.domain.modal.PageDto;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.util.Assert;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * The type Utility.
 */
@Slf4j
public class Utility {

	private Utility() {

	}

	/**
	 * Print stack trace.
	 * @param e the e
	 */
	public static void printStackTrace(Exception e) {
		String stackTrace = Arrays.stream(e.getStackTrace())
			.map(StackTraceElement::toString)
			.collect(Collectors.joining("\n"));
		log.error(stackTrace);
	}

	/**
	 * Sanitize url string.
	 * @param url the url
	 * @return the string
	 * @throws URISyntaxException the uri syntax exception
	 */
	public static String sanitizeUrl(String url) throws URISyntaxException {
		URI uri = new URI(url);
		// Rebuild the URL without query and fragment
		URI sanitizedUri = new URI(uri.getScheme(), uri.getAuthority(), uri.getPath(), null, null);
		return sanitizedUri.toString();
	}

	/**
	 * Gets pageable.
	 *
	 * @param page          the page
	 * @param size          the size
	 * @param sortBy        the sort by
	 * @param sortDirection the sort direction
	 * @return the pageable
	 */
	public  static Pageable getPageable(int page, int size, String sortBy, String sortDirection) {
		sortDirection = sortDirection != null ? sortDirection : "ASC";
		Sort sort = Objects.isNull(sortBy) ? Sort.unsorted() : Sort.by(sortBy).ascending();
		if(!sortDirection.equalsIgnoreCase("ASC")){
			sort.descending();
		}
		int DEFAULT_SIZE = 10;
		return PageRequest.of(Math.max(page, 0),
				size > 0 ? size : DEFAULT_SIZE, sort);
	}


}
