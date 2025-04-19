package org.loop.troop.book.domain;

import lombok.extern.slf4j.Slf4j;

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

}
