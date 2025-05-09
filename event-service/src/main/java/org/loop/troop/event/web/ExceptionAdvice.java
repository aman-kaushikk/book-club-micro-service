package org.loop.troop.event.web;

import jakarta.annotation.PostConstruct;
import jakarta.validation.ConstraintViolationException;
import org.loop.troop.event.domain.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;
import java.util.Objects;

import static org.loop.troop.event.web.ExceptionHandlerDSL.createProblemDetail;

/**
 * The type Exception advice.
 */
@RestControllerAdvice
public class ExceptionAdvice {

	/**
	 * Handle exception response entity.
	 * @param ex the ex
	 * @return the response entity
	 */
	@ExceptionHandler(Exception.class)
	ResponseEntity<ProblemDetail> handleException(Exception ex) {
		return ExceptionHandlerDSL.handle(ex);
	}

	/**
	 * Sets exception handling.
	 */
	@PostConstruct
	public void setupExceptionHandling() {
		ExceptionHandlerDSL.configure(handler -> {
			// handling service exception
			handler.on(ServiceException.class).respondWithStatus(HttpStatus.BAD_REQUEST);
			// handling jakarta validation
			handler.on(MethodArgumentNotValidException.class)
				.respondWith(ExceptionAdvice::methodArgumentNotValidException);
			handler.on(ConstraintViolationException.class)
				.respondWith(ExceptionAdvice::handleConstraintViolationException);
			// handling default exception
			handler.defaultResponse(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred");
		});
	}

	private static ProblemDetail methodArgumentNotValidException(Throwable throwable) {
		if (throwable instanceof MethodArgumentNotValidException methodArgumentNotValidException) {
			var errors = methodArgumentNotValidException.getBindingResult()
				.getFieldErrors()
				.stream()
				.map(fieldError -> Map.of("field", fieldError.getField(), "message",
						Objects.requireNonNull(fieldError.getDefaultMessage())))
				.toList();
			return createProblemDetail(HttpStatus.BAD_REQUEST, "Validation Error - Cannot validate request object",
					throwable, errors);

		}
		else {
			return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, throwable.getMessage());
		}
	}

	private static ProblemDetail handleConstraintViolationException(Throwable throwable) {
		if (throwable instanceof ConstraintViolationException constraintViolationException) {
			var errors = constraintViolationException.getConstraintViolations().stream().map(violation -> {
				String field = violation.getPropertyPath().toString();
				String messageCode = violation.getMessage(); // Usually a message key
				Object[] args = violation.getConstraintDescriptor().getAttributes().values().toArray();
				return Map.of("field", field, "message", MessageResolver.getMessage(messageCode, args));
			}).toList();
			return createProblemDetail(HttpStatus.BAD_REQUEST, "Validation Error - Cannot validate request object",
					throwable, errors);
		}
		else {
			return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, throwable.getMessage());
		}
	}

}
