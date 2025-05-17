package org.loop.troop.book.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * The type Exception handler dsl.
 */
@Slf4j
class ExceptionHandlerDSL {

	private static final Map<Class<? extends Throwable>, Function<Throwable, ProblemDetail>> exceptionMappings = new HashMap<>();

	private static Function<Throwable, ProblemDetail> defaultResponse;

	/**
	 * Configure.
	 * @param builderConsumer the builder consumer
	 */
	static void configure(Consumer<Builder> builderConsumer) {
		Builder builder = new Builder();
		builderConsumer.accept(builder);
	}

	/**
	 * Handle response entity.
	 * @param exception the exception
	 * @return the response entity
	 */
	static ResponseEntity<ProblemDetail> handle(Throwable exception) {
		Function<Throwable, ProblemDetail> mapper = exceptionMappings.getOrDefault(exception.getClass(),
				defaultResponse);
		ProblemDetail problem = mapper.apply(exception);
		return new ResponseEntity<>(problem, HttpStatus.valueOf(problem.getStatus()));
	}

	/**
	 * The type Builder.
	 */
	static class Builder {

		/**
		 * On response configurer.
		 * @param <T> the type parameter
		 * @param exceptionClass the exception class
		 * @return the response configurer
		 */
		public <T extends Throwable> ResponseConfigurer<T> on(Class<T> exceptionClass) {
			return new ResponseConfigurer<>(exceptionClass);
		}

		/**
		 * Default response.
		 * @param status the status
		 * @param message the message
		 */
		public void defaultResponse(HttpStatus status, String message) {
			defaultResponse = ex -> createProblemDetail(status, message, ex, null);
		}

	}

	/**
	 * The type Response configurer.
	 *
	 * @param <T> the type parameter
	 */
	static class ResponseConfigurer<T extends Throwable> {

		private final Class<T> exceptionClass;

		/**
		 * Instantiates a new Response configurer.
		 * @param exceptionClass the exception class
		 */
		ResponseConfigurer(Class<T> exceptionClass) {
			this.exceptionClass = exceptionClass;
		}

		/**
		 * Respond with status.
		 * @param status the status
		 */
		public void respondWithStatus(HttpStatus status) {
			exceptionMappings.put(exceptionClass, ex -> createProblemDetail(status, ex.getMessage(), ex, null));
		}

		/**
		 * Respond with status and message.
		 * @param status the status
		 * @param message the message
		 */
		public void respondWithStatusAndMessage(HttpStatus status, String message) {
			exceptionMappings.put(exceptionClass, ex -> createProblemDetail(status, message, ex, null));
		}

		/**
		 * Respond with.
		 * @param problemBuilder the problem builder
		 */
		public void respondWith(Function<Throwable, ProblemDetail> problemBuilder) {
			exceptionMappings.put(exceptionClass, problemBuilder);
		}

	}

	/**
	 * Create problem detail.
	 * @param status the status
	 * @param message the message
	 * @param ex the ex
	 * @param errors the errors
	 * @return the problem detail
	 */
	static ProblemDetail createProblemDetail(HttpStatus status, String message, Throwable ex,
			List<Map<String, String>> errors) {
		ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(status, message);
		problemDetail.setTitle(status.getReasonPhrase());
		problemDetail.setProperty("timestamp", Instant.now().toString());
		problemDetail.setProperty("exception", ex.getClass().getSimpleName());
		problemDetail.setType(URI.create("https://book-club.com/errors"));
		if (errors != null && !errors.isEmpty()) {
			problemDetail.setProperty("errors", errors);
		}

		if (status.is5xxServerError()) {
			log.error("CRITICAL EXCEPTION: Server error occurred", ex); // Log the stack
																		// trace
		}
		else {
			log.info("HANDLED EXCEPTION: Server error occurred", ex);
		}

		return problemDetail;
	}

}
