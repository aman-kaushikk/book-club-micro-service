package org.loop.troop.book.domain;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.loop.troop.book.domain.enums.EventProcessingStatus;
import org.loop.troop.book.domain.modal.EventLogDto;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Set;
import java.util.UUID;

@RequiredArgsConstructor
@Slf4j
public abstract class BookRoutingStrategy {

	private final Validator validator;

	private final EventLogRepository eventLogRepository;

	private final EventLogMapper eventLogMapper;

	protected abstract void execute(EventLogDto eventLogDto);

	abstract EventLogDto massageToEventDto(String message);

	public abstract boolean supportRoutingKey(String key);

	public abstract String name();

	protected abstract boolean validatePayload(String payload);

	protected final boolean validateEventLogDto(EventLogDto eventLogDto) {
		boolean isValid = true;
		Set<ConstraintViolation<EventLogDto>> violations = validator.validate(eventLogDto);
		if (!violations.isEmpty()) {
			violations.forEach(violation -> log.error("Field violations message : {}", violation.getMessage()));
			isValid = false;
		}
		if (!validatePayload(eventLogDto.getPayload())) {
			log.error("Failed to valid payload: {}", eventLogDto.getPayload());
			isValid = false;
		}
		return isValid;
	}

	protected final EventLogDto process(String message, UUID eventId) {
		EventLog saveEventLog = null;
		try {
			EventLog eventLog = getEvent(message, eventId);
			eventLog.setProcessingStatus(EventProcessingStatus.valueOf(EventProcessingStatus.ONGOING.name()));
			saveEventLog = eventLogRepository.save(eventLog);
			EventLogDto eventDto = eventLogMapper.getEventDto(saveEventLog);
			execute(eventDto);
			saveEventLog.setProcessingStatus(EventProcessingStatus.valueOf(EventProcessingStatus.COMPLETED.name()));
			eventLogRepository.save(saveEventLog);
			return eventDto;
		}
		catch (Exception e) {
			log.info("Error in processing message: {}", e.getMessage());
			Assert.notNull(saveEventLog, "Event log is mandatory cannot be null");
			saveEventLog.setErrorMessage(e.getMessage());
			saveEventLog.setProcessingStatus(EventProcessingStatus.valueOf(EventProcessingStatus.ERROR.name()));
			eventLogRepository.save(saveEventLog);
			throw e;
		}
	}

	private EventLog getEvent(String message, UUID eventId) {
		EventLogDto eventLogDto = massageToEventDto(message);
		if (!validateEventLogDto(eventLogDto)) {
			log.error("Failed to validate event-log dto: {}", eventLogDto);
			throw new ServiceException("Failed to valid event-log dto from message");
		}
		eventLogDto.setEventId(eventId);
		EventLog eventLog = eventLogMapper.getEvent(eventLogDto);
		eventLog = eventLogRepository.save(eventLog);
		log.info("Dto event id : {}", eventLogDto.getEventId());
		log.info("event log entity id: {}", eventLog.getEventId());
		return eventLog;
	}

}
