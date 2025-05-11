package org.loop.troop.event.domain;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.loop.troop.event.domain.enums.EventProcessingStatus;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static org.loop.troop.event.domain.enums.EventProcessingStatus.ONGOING;
import static org.loop.troop.event.domain.enums.EventProcessingStatus.PENDING;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookEventScheduler {

	private final EventLogService eventLogService;

	private final AmqpTemplate amqpTemplate;

	private static final String SENDING_MESSAGE = "Sending message to RabbitMQ. Routing key: {}";

	private static final String SEND_MESSAGE = "Message sent: {}";

	@Scheduled(cron = "*/5 * * * * *")
	public void bookEventScheduler() {
		log.info("Running scheduler for book  related event");
		List<EventLog> currentEvents = eventLogService.getPendingEvent();
		currentEvents.forEach(this::publishEvent);
	}

	@Scheduled(cron = "0 0 22 * * *")
	public void runAtTenPM() {
		List<EventLog> currentEvents = eventLogService.getCompletedEvent();
		eventLogService.deleteEvent(currentEvents);
	}

	private void publishEvent(EventLog eventLog) {
		UUID eventId = eventLog.getEventId();
		String app = eventLog.getApp();
		String routingKey = eventLog.getRoutingKey();
		String exchange = eventLog.getExchange();
		String payload = eventLog.getPayload();
		log.info(SEND_MESSAGE, payload);
		Message message = MessageBuilder.withBody(payload.getBytes())
			.setHeader("app", app)
			.setHeader("event-id", eventId)
			.build();
		try {
			sendRabbitmqMessage(routingKey, exchange, message);
			log.info("Message published successfully for eventId {}", eventId);
		}
		catch (Exception e) {
			log.error("Failed to publish message for eventId {}: {}", eventId, e.getMessage(), e);
		}
	}

	private void sendRabbitmqMessage(String routingKey, String exchange, Message message) {
		log.info(SENDING_MESSAGE, routingKey);
		amqpTemplate.convertAndSend(exchange, routingKey, message);
	}

}
