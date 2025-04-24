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

@Slf4j
class ExceptionHandlerDSL {

    private static final Map<Class<? extends Throwable>, Function<Throwable, ProblemDetail>> exceptionMappings = new HashMap<>();
    private static Function<Throwable, ProblemDetail> defaultResponse;


    static void configure(Consumer<Builder> builderConsumer) {
        Builder builder = new Builder();
        builderConsumer.accept(builder);
    }

    static ResponseEntity<ProblemDetail> handle(Throwable exception) {
        Function<Throwable, ProblemDetail> mapper = exceptionMappings.getOrDefault(
            exception.getClass(), defaultResponse
        );
        ProblemDetail problem = mapper.apply(exception);
        return new ResponseEntity<>(problem, HttpStatus.valueOf(problem.getStatus()));
    }

    static class Builder {
        public <T extends Throwable> ResponseConfigurer<T> on(Class<T> exceptionClass) {
            return new ResponseConfigurer<>(exceptionClass);
        }

        public void defaultResponse(HttpStatus status, String message) {
            defaultResponse = ex -> createProblemDetail(status, message, ex, null);
        }
    }

    static class ResponseConfigurer<T extends Throwable> {
        private final Class<T> exceptionClass;

        ResponseConfigurer(Class<T> exceptionClass) {
            this.exceptionClass = exceptionClass;
        }

        public void respondWithStatus(HttpStatus status) {
            exceptionMappings.put(exceptionClass, ex -> createProblemDetail(status, ex.getMessage(), ex, null));
        }
        public void respondWithStatusAndMessage(HttpStatus status,String message) {
            exceptionMappings.put(exceptionClass, ex -> createProblemDetail(status, message, ex, null));
        }
        public void respondWith(Function<Throwable, ProblemDetail> problemBuilder) {
            exceptionMappings.put(exceptionClass,problemBuilder);
        }
    }

    static ProblemDetail createProblemDetail(HttpStatus status, String message, Throwable ex, List<Map<String, String>> errors) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(status, message);
        problemDetail.setTitle(status.getReasonPhrase());
        problemDetail.setProperty("timestamp", Instant.now().toString());
        problemDetail.setProperty("exception", ex.getClass().getSimpleName());
        problemDetail.setType(URI.create("https://book-club.com/errors"));
        if (errors != null && !errors.isEmpty()) {
            problemDetail.setProperty("errors", errors);
        }

        if (status.is5xxServerError()) {
            log.error("CRITICAL EXCEPTION: Server error occurred", ex); // Log the stack trace
        }else{
            log.info("HANDLED EXCEPTION: Server error occurred", ex);
        }

        return problemDetail;
    }


}
