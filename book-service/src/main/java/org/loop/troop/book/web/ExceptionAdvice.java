package org.loop.troop.book.web;

import jakarta.annotation.PostConstruct;
import jakarta.validation.ConstraintViolationException;
import org.loop.troop.book.domain.ServiceException;
import org.loop.troop.book.domain.UrlScrapingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

import static org.loop.troop.book.web.ExceptionHandlerDSL.createProblemDetail;
import static org.loop.troop.book.web.MessageResolver.getMessage;

@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(Exception.class)
    ResponseEntity<ProblemDetail> handleException(Exception ex){
        return ExceptionHandlerDSL.handle(ex);
    }
    @PostConstruct
    public void setupExceptionHandling() {
        ExceptionHandlerDSL.configure(handler -> {
            // handling service exception
            handler.on(ServiceException.class)
                    .respondWithStatus(HttpStatus.BAD_REQUEST);
            // handling scraping exception
            handler.on(UrlScrapingException.class)
                    .respondWithStatus(HttpStatus.BAD_GATEWAY);
            // handling jakarta validation
            handler.on(MethodArgumentNotValidException.class)
                    .respondWith(ExceptionAdvice::MethodArgumentNotValidException);
            handler.on(ConstraintViolationException.class)
                    .respondWith(ExceptionAdvice::MethodArgumentNotValidException);
            // handling default exception
            handler.defaultResponse(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred");
        });
    }


    private static ProblemDetail MethodArgumentNotValidException(Throwable throwable){
        if(throwable instanceof MethodArgumentNotValidException methodArgumentNotValidException){
            var errors = methodArgumentNotValidException.getBindingResult().getFieldErrors()
                    .stream()
                    .map(fieldError -> Map.of(
                            "field", fieldError.getField(),
                            "message", getMessage(fieldError.getDefaultMessage(),fieldError.getArguments())
                    ))
                    .toList();
            return createProblemDetail(HttpStatus.BAD_REQUEST,"Validation Error - Cannot validate request object",throwable,errors);

        }else{
            return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, throwable.getMessage());
        }
    }

    private static ProblemDetail handleConstraintViolationException(Throwable throwable) {
        if (throwable instanceof ConstraintViolationException constraintViolationException) {
            var errors = constraintViolationException.getConstraintViolations().stream()
                    .map(violation -> {
                        String field = violation.getPropertyPath().toString();
                        String messageCode = violation.getMessage(); // Usually a message key
                        Object[] args = violation.getConstraintDescriptor().getAttributes()
                                .values()
                                .toArray(); // Pull arguments like min, max, etc.

                        return Map.of(
                                "field", field,
                                "message", MessageResolver.getMessage(messageCode, args)
                        );
                    })
                    .toList();
            return createProblemDetail(HttpStatus.BAD_REQUEST, "Validation Error - Cannot validate request object", throwable, errors);
        } else {
            return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, throwable.getMessage());
        }
    }

}
