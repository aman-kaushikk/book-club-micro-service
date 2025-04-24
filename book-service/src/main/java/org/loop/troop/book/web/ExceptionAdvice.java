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
import java.util.Objects;

import static org.loop.troop.book.web.ExceptionHandlerDSL.createProblemDetail;

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
            // handling default exceptoin
            handler.defaultResponse(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred");
        });
    }


    private static ProblemDetail MethodArgumentNotValidException(Throwable throwable){
        if(throwable instanceof MethodArgumentNotValidException methodArgumentNotValidException){
            var errors = methodArgumentNotValidException.getBindingResult().getFieldErrors()
                    .stream()
                    .map(fieldError -> Map.of(
                            "field", fieldError.getField(),
                            "message", Objects.requireNonNull(fieldError.getDefaultMessage())
                    ))
                    .toList();
            return createProblemDetail(HttpStatus.BAD_REQUEST,throwable.getMessage(),throwable,errors);

        }else{
            return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, throwable.getMessage());
        }
    }

    private static ProblemDetail handleConstraintViolationException(Throwable throwable) {
        if (throwable instanceof ConstraintViolationException constraintViolationException) {
            var errors = constraintViolationException.getConstraintViolations().stream()
                    .map(violation -> Map.of(
                            "field", violation.getPropertyPath().toString(),
                            "message", violation.getMessage()
                    ))
                    .toList();
            return createProblemDetail(HttpStatus.BAD_REQUEST, throwable.getMessage(), throwable, errors);
        } else {
            return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, throwable.getMessage());
        }
    }

}
