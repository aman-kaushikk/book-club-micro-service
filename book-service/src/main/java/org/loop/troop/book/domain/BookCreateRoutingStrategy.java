package org.loop.troop.book.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.loop.troop.book.domain.enums.Vendor;
import org.loop.troop.book.domain.modal.BookRequest;
import org.loop.troop.book.domain.modal.EventLogDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.Set;

import static org.loop.troop.book.domain.enums.EventProcessingStatus.PENDING;
import static org.loop.troop.book.domain.enums.EventType.BOOK_CREATE;

@Service
@Slf4j
class BookCreateRoutingStrategy extends BookRoutingStrategy {

	@Value("${app.rabbitmq.book-service-create-routing-key}")
	private String supportedRoutingKey;

	private final ObjectMapper objectMapper;

	private final BookService bookService;

	private final Validator validator;

	public BookCreateRoutingStrategy(Validator validator, EventLogRepository eventLogRepository,
			EventLogMapper eventLogMapper, ObjectMapper objectMapper, BookService bookService) {
		super(validator, eventLogRepository, eventLogMapper);
		this.objectMapper = objectMapper;
		this.bookService = bookService;
		this.validator = validator;
	}

	@Override
	protected void execute(EventLogDto eventLogDto) {
		String payload = eventLogDto.getPayload();
		try {
			BookRequest bookRequest = objectMapper.readValue(payload, BookRequest.class);
			Set<ConstraintViolation<BookRequest>> violations = validator.validate(bookRequest);
			if (!violations.isEmpty()) {
				violations.forEach(violation -> log.error("Field violations message : {}", violation.getMessage()));
				throw new ServiceException("Failed to valid book-request from the payload");
			}
			bookService.register(bookRequest.getUrl(), Vendor.valueOf(bookRequest.getVendor()));
		}
		catch (JsonProcessingException e) {
			log.error("Payload cannot be processed to book-request class: {}", e.getMessage());
			throw new ServiceException("Processing payload to book-request failed");
		}
	}

	@Override
	EventLogDto massageToEventDto(String message) {
		EventLogDto eventLogDto = new EventLogDto();
		eventLogDto.setEventType(BOOK_CREATE.name());
		eventLogDto.setPayload(message);
		eventLogDto.setRoutingKey(supportedRoutingKey);
		eventLogDto.setTimestamp(LocalDateTime.now());
		eventLogDto.setProcessingStatus(PENDING.name());
		return eventLogDto;
	}

	@Override
	protected boolean validatePayload(String payload) {
		Assert.notNull(payload, "Payload is mandatory to valid");
		try {
			objectMapper.readValue(payload, BookRequest.class);
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
