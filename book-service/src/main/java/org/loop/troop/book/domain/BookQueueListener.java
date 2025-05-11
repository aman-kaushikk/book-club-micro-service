package org.loop.troop.book.domain;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.loop.troop.book.domain.enums.EventProcessingStatus;
import org.loop.troop.book.domain.service.BookRoutingStrategy;
import org.loop.troop.book.web.AppConfig;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookQueueListener {

	private final AppConfig appConfig;

	private final List<BookRoutingStrategy> bookRoutingStrategies;

	private final BookServiceEventClient bookServiceEventClient;

	private static final String EVENT_ID = "event-id";

	private static final String APP = "app";

	@RabbitListener(queues = "#{appConfig.getRabbitmq().getBookQueue()}")
	public void listenToBookQueue(Message message, @Header(AmqpHeaders.RECEIVED_ROUTING_KEY) String routingKey,
			@Header(EVENT_ID) UUID eventId, @Header(APP) String app) {
		String payload = new String(message.getBody());
		log.info("Received message: {}", payload);
		log.info("Routing Key: {}", routingKey);
		log.info("Header eventId: {}", eventId);
		log.info("Header app: {}", app);

		for (var bookRoutingStrategy : bookRoutingStrategies) {
			if (bookRoutingStrategy.supportRoutingKey(routingKey)) {
				try {
					log.info("Book Strategy matched: {}", bookRoutingStrategy.name());
					bookRoutingStrategy.process(payload, eventId);
					log.info("payload in listener: {}", payload);
					bookServiceEventClient.updateBookProcessingStatus(eventId, EventProcessingStatus.COMPLETED.name(),null);
				}
				catch (Exception e) {
					bookServiceEventClient.updateBookProcessingStatus(eventId, EventProcessingStatus.ERROR.name(),e.getMessage());
					log.info("Error while processing event: {}", e.getMessage());
					throw new ServiceException("Error while processing event with event id: " + eventId);
				}
				return;
			}
		}
		throw new ServiceException("Currently no matching strategy to handle this event");

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
