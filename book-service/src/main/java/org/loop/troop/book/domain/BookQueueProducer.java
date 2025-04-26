package org.loop.troop.book.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.loop.troop.book.domain.modal.BookRequest;
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

	private static final String SENDING_MESSAGE = "Sending message to RabbitMQ. Routing key: {}";

	private static final String SEND_MESSAGE = "Message sent: {}";

	public void sendBookCreatedMessage(BookRequest bookRequest, UUID eventId) {
		byte[] bytes;
		String bookMessage;
		try {
			bytes = objectMapper.writeValueAsBytes(bookRequest);
			bookMessage = objectMapper.writeValueAsString(bookRequest);
		}
		catch (JsonProcessingException e) {
			log.error("Payload cannot be processed to book-request class: {}", e.getMessage());
			throw new ServiceException("Processing payload to book-request failed");
		}
		var rabbitmqConfig = appConfig.getRabbitmq();
		String routingKey = rabbitmqConfig.getCreateRoutingKey();
		String exchange = rabbitmqConfig.getBookExchange();

		Message message = MessageBuilder.withBody(bytes)
			.setHeader("app", "book-service")
			.setHeader("event-id", eventId)
			.build();

		sendRabbitmqMessage(bookMessage, routingKey, exchange, message);
	}

	public void sendBookUpdatedMessage(String bookMessage) {
		String routingKey = appConfig.getRabbitmq().getUpdateRoutingKey(); // Get routing
																			// key
		String exchange = appConfig.getRabbitmq().getBookExchange(); // Get exchange name

		Message message = MessageBuilder.withBody(bookMessage.getBytes()).setHeader("app", "book-service").build();

		sendRabbitmqMessage(bookMessage, routingKey, exchange, message);
	}

	private void sendRabbitmqMessage(String bookMessage, String routingKey, String exchange, Message message) {
		log.info(SENDING_MESSAGE, routingKey);
		amqpTemplate.convertAndSend(exchange, routingKey, message);
		log.info(SEND_MESSAGE, bookMessage);
	}

}
