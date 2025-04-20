package org.loop.troop.book.domain;

import lombok.extern.slf4j.Slf4j;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
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

	public static String sanitizeUrl(String url) throws URISyntaxException {
		URI uri = new URI(url);
		// Rebuild the URL without query and fragment
		URI sanitizedUri = new URI(uri.getScheme(), uri.getAuthority(), uri.getPath(), null, null);
		return sanitizedUri.toString();
	}

}
