package org.loop.troop.book.domain;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.loop.troop.book.web.AppConfig;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class BookQueueProducer {

	@Autowired
	private final AmqpTemplate amqpTemplate;

	@Autowired
	private final AppConfig appConfig;

	private static final String SENDING_MESSAGE = "Sending message to RabbitMQ. Routing key: {}";

	private static final String SEND_MESSAGE = "Message sent: {}";

	public void sendBookCreatedMessage(String bookMessage) {
		var rabbitmqConfig = appConfig.getRabbitmq();
		String routingKey = rabbitmqConfig.getCreateRoutingKey();
		String exchange = rabbitmqConfig.getBookExchange();

		Message message = MessageBuilder.withBody(bookMessage.getBytes()).setHeader("app", "book-service").build();

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
