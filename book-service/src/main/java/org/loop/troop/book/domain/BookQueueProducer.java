package org.loop.troop.book.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.loop.troop.book.domain.enums.EventProcessingStatus;
import org.loop.troop.book.domain.enums.EventType;
import org.loop.troop.book.domain.request.book.BookRequest;
import org.loop.troop.book.domain.modal.EventLogDto;
import org.loop.troop.book.domain.request.book.BookUpdateRequest;
import org.loop.troop.book.web.AppConfig;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
@Validated
public class BookQueueProducer {

	private final AmqpTemplate amqpTemplate;

	private final AppConfig appConfig;

	private final ObjectMapper objectMapper;

	private final BookServiceEventClient bookServiceEventClient;

	private static final String SENDING_MESSAGE = "Sending message to RabbitMQ. Routing key: {}";

	private static final String SEND_MESSAGE = "Message sent: {}";

	public void sendBookCreatedMessage(@Valid BookRequest bookRequest,@NotNull UUID eventId) {
		String createBookPayload;
		try {
			createBookPayload = objectMapper.writeValueAsString(bookRequest);
		}
		catch (JsonProcessingException e) {
			log.error("Payload cannot be processed to book-request class: {}", e.getMessage());
			throw new ServiceException("Processing payload to book-request failed");
		}
		var rabbitmqConfig = appConfig.getRabbitmq();
		String routingKey = rabbitmqConfig.getCreateRoutingKey();
		String exchange = rabbitmqConfig.getBookExchange();
		String app = "book-service";
		EventLogDto eventLogDto = createEventLogDto(app, routingKey, exchange, createBookPayload,EventType.BOOK_CREATE);
		log.info("request body for create book: {}", eventLogDto);
		bookServiceEventClient.saveBookRegisterEvent(eventId, eventLogDto);
	}

	public void sendBookUpdatedMessage(@Valid BookUpdateRequest bookUpdateRequest,@NotNull UUID eventId) {
		String updateBookPayload;
		try {
			updateBookPayload = objectMapper.writeValueAsString(bookUpdateRequest);
		}
		catch (JsonProcessingException e) {
			log.error("Payload cannot be processed to book-update class: {}", e.getMessage());
			throw new ServiceException("Processing payload to book-update failed");
		}
		var rabbitmqConfig = appConfig.getRabbitmq();
		String routingKey = rabbitmqConfig.getUpdateRoutingKey();
		String exchange = rabbitmqConfig.getBookExchange();
		String app = "book-service";
		EventLogDto eventLogDto = createEventLogDto(app, routingKey, exchange, updateBookPayload,EventType.BOOK_UPDATE);
		log.info("request body for update book: {}", eventLogDto);
		bookServiceEventClient.saveBookRegisterEvent(eventId, eventLogDto);
	}

	private EventLogDto createEventLogDto(String app, String routingKey, String exchange, String body,EventType eventType) {
		EventLogDto eventLogDto = new EventLogDto();
		eventLogDto.setApp(app);
		eventLogDto.setRoutingKey(routingKey);
		eventLogDto.setExchange(exchange);
		eventLogDto.setPayload(body);
		eventLogDto.setProcessingStatus(EventProcessingStatus.PENDING.name());
		eventLogDto.setEventType(eventType.name());
		return eventLogDto;
	}

	private void sendRabbitmqMessage(String bookMessage, String routingKey, String exchange, Message message) {
		log.info(SENDING_MESSAGE, routingKey);
		amqpTemplate.convertAndSend(exchange, routingKey, message);
		log.info(SEND_MESSAGE, bookMessage);
	}

}
