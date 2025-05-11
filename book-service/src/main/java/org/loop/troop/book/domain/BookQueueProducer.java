package org.loop.troop.book.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.loop.troop.book.domain.enums.EventProcessingStatus;
import org.loop.troop.book.domain.enums.EventType;
import org.loop.troop.book.domain.modal.BookRequest;
import org.loop.troop.book.domain.modal.EventLogDto;
import org.loop.troop.book.web.AppConfig;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class BookQueueProducer {

	private final AmqpTemplate amqpTemplate;

	private final AppConfig appConfig;

	private final ObjectMapper objectMapper;

	private final BookServiceEventClient bookServiceEventClient;

	private static final String SENDING_MESSAGE = "Sending message to RabbitMQ. Routing key: {}";

	private static final String SEND_MESSAGE = "Message sent: {}";

	public void sendBookCreatedMessage(BookRequest bookRequest, UUID eventId) {
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
		EventLogDto eventLogDto = createEventLogDto(app, routingKey, exchange, createBookPayload);
		log.info("request body for create book: {}", eventLogDto);
		bookServiceEventClient.saveBookRegisterEvent(eventId, eventLogDto);
	}

	public void sendBookUpdatedMessage(String bookMessage) {
		String routingKey = appConfig.getRabbitmq().getUpdateRoutingKey(); // Get routing
																			// key
		String exchange = appConfig.getRabbitmq().getBookExchange(); // Get exchange name

		Message message = MessageBuilder.withBody(bookMessage.getBytes()).setHeader("app", "book-service").build();

		sendRabbitmqMessage(bookMessage, routingKey, exchange, message);
	}

	private EventLogDto createEventLogDto(String app, String routingKey, String exchange, String body) {
		EventLogDto eventLogDto = new EventLogDto();
		eventLogDto.setApp(app);
		eventLogDto.setRoutingKey(routingKey);
		eventLogDto.setExchange(exchange);
		eventLogDto.setPayload(body);
		eventLogDto.setProcessingStatus(EventProcessingStatus.PENDING.name());
		eventLogDto.setEventType(EventType.BOOK_CREATE.name());
		return eventLogDto;
	}

	private void sendRabbitmqMessage(String bookMessage, String routingKey, String exchange, Message message) {
		log.info(SENDING_MESSAGE, routingKey);
		amqpTemplate.convertAndSend(exchange, routingKey, message);
		log.info(SEND_MESSAGE, bookMessage);
	}

}
