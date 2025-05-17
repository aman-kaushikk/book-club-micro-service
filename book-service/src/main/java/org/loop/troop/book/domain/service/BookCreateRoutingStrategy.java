package org.loop.troop.book.domain.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.loop.troop.book.domain.BookService;
import org.loop.troop.book.domain.ServiceException;
import org.loop.troop.book.domain.enums.Vendor;
import org.loop.troop.book.domain.request.book.BookRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Set;
import java.util.UUID;

@Service
@Slf4j
class BookCreateRoutingStrategy extends BookRoutingStrategy {

	@Value("${app.rabbitmq.book-service-create-routing-key}")
	private String supportedRoutingKey;

	private final ObjectMapper objectMapper;

	private final BookService bookService;

	private final Validator validator;

	public BookCreateRoutingStrategy(ObjectMapper objectMapper, BookService bookService, Validator validator) {
		this.objectMapper = objectMapper;
		this.bookService = bookService;
		this.validator = validator;
	}

	@Override
	protected void execute(String payload, UUID eventId) {
		log.info("Executing event with event id: {}", eventId);
		try {
			BookRequest bookRequest = objectMapper.readValue(payload, BookRequest.class);
			bookService.register(bookRequest.getUrl(), Vendor.valueOf(bookRequest.getVendor()));
		}
		catch (JsonProcessingException e) {
			log.error("Payload cannot be processed to book-request class: {}", e.getMessage());
			throw new ServiceException("Processing payload to book-request failed");
		}
	}

	@Override
	protected boolean validatePayload(String payload) {
		Assert.notNull(payload, "Payload is mandatory to valid");
		try {
			BookRequest bookRequest = objectMapper.readValue(payload, BookRequest.class);
			Set<ConstraintViolation<BookRequest>> violations = validator.validate(bookRequest);
			if (!violations.isEmpty()) {
				violations.forEach(violation -> log.error("Field violations message : {}", violation.getMessage()));
				throw new ServiceException("Failed to valid book-request from the payload");
			}
			if (bookService.isBookPresent(bookRequest.getUrl())) {
				throw new ServiceException("Cannot Register book: book exists by same url");
			}
		}
		catch (JsonProcessingException e) {
			log.info("Payload cannot be processed to book-request class: {}", e.getMessage());
			return false;
		}
		return true;
	}

	@Override
	public boolean supportRoutingKey(String key) {
		return supportedRoutingKey.equalsIgnoreCase(key);
	}

	@Override
	public String name() {
		return "BOOK-CREATED-EVENT-HANDLER";
	}

}
