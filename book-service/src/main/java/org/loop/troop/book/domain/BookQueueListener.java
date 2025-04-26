package org.loop.troop.book.domain;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.loop.troop.book.web.AppConfig;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookQueueListener {

	private final AppConfig appConfig;

	@RabbitListener(queues = "#{appConfig.getRabbitmq().getBookQueue()}")
	public void listenToBookQueue(Message message, @Header(AmqpHeaders.RECEIVED_ROUTING_KEY) String routingKey) {
		// Access the routing key from the message header
		log.info("Received message: {}", new String(message.getBody()));
		log.info("Routing Key: {}", routingKey);

		if (routingKey.equals(appConfig.getRabbitmq().getCreateRoutingKey())) {
			// Handle book creation logic
			log.info("Book created message received.");
		}
		else if (routingKey.equals(appConfig.getRabbitmq().getUpdateRoutingKey())) {
			// Handle book update logic
			log.info("Book updated message received.");
		}
		else {
			log.info("Unknown routing key.");
		}
	}

	@RabbitListener(queues = "#{appConfig.getRabbitmq().getBookDeadLetterQueue()}")
	public void listenToDeadLetterQueue(Message message, @Header(AmqpHeaders.RECEIVED_ROUTING_KEY) String routingKey,
			@Header(AmqpHeaders.RECEIVED_EXCHANGE) String exchange,
			@Header(value = "x-death", required = false) List<Map<String, Object>> xDeathHeader) {
		log.error("[DLX] Received message: {}", new String(message.getBody()));
		log.error("Routing Key: {}", routingKey);
		log.error("Exchange: {}", exchange);

		if (xDeathHeader != null && !xDeathHeader.isEmpty()) {
			Map<String, Object> deathInfo = xDeathHeader.getFirst(); // first failure
			log.error("x-death info: {}", deathInfo);

			log.error("Failed Queue: {}", deathInfo.get("queue"));
			log.error("Retry Count: {}", deathInfo.get("count"));
			log.error("Original Exchange: {}", deathInfo.get("exchange"));
			log.error("Reason: {}", deathInfo.get("reason"));
		}
		else {
			log.warn("No x-death header found.");
		}
	}

}
