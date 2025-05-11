package org.loop.troop.book.domain.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.loop.troop.book.domain.BookService;
import org.loop.troop.book.domain.ServiceException;
import org.loop.troop.book.domain.request.book.BookUpdateRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Set;
import java.util.UUID;

@Service
@Slf4j
public class BookUpdateRoutingStrategy extends BookRoutingStrategy{

    @Value("${app.rabbitmq.book-service-update-routing-key}")
    private String supportedRoutingKey;

    private final ObjectMapper objectMapper;

    private final BookService bookService;

    private final Validator validator;

    public BookUpdateRoutingStrategy(ObjectMapper objectMapper, BookService bookService, Validator validator) {
        this.objectMapper = objectMapper;
        this.bookService = bookService;
        this.validator = validator;
    }

    @Override
    public boolean supportRoutingKey(String key) {
        return supportedRoutingKey.equalsIgnoreCase(key);
    }

    @Override
    public String name() {
        return "BOOK-UPDATE-EVENT-HANDLER";
    }

    @Override
    protected boolean validatePayload(String payload) {
        Assert.notNull(payload, "Payload is mandatory to valid");
        try {
            BookUpdateRequest bookRequest = objectMapper.readValue(payload, BookUpdateRequest.class);
            Set<ConstraintViolation<BookUpdateRequest>> violations = validator.validate(bookRequest);
            if (!violations.isEmpty()) {
                violations.forEach(violation -> log.error("Field violations message : {}", violation.getMessage()));
                throw new ServiceException("Failed to valid book-request from the payload");
            }
        }
        catch (JsonProcessingException e) {
            log.info("Payload cannot be processed to book-request class: {}", e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    protected void execute(String payload, UUID eventId) {
        log.info("Executing event with event id: {}", eventId);
        try {
            BookUpdateRequest bookUpdateRequest = objectMapper.readValue(payload, BookUpdateRequest.class);
            log.info("book-update-request: {}",bookUpdateRequest);
            bookService.updateBook(bookUpdateRequest);
        }catch (JsonProcessingException e) {
            log.error("Payload cannot be processed to book-request class: {}", e.getMessage());
            throw new ServiceException("Processing payload to book-request failed");
        }
    }
}
