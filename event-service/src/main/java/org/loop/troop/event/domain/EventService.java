package org.loop.troop.event.domain;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.loop.troop.event.domain.enums.EventProcessingStatus;
import org.loop.troop.event.domain.modal.EventLogDto;
import org.loop.troop.event.web.validator.ValidEnum;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.UUID;

@Service
@Validated
@RequiredArgsConstructor
@Slf4j
public class EventService {

	private final EventLogService eventLogService;

	private final EventLogMapper eventLogMapper;

	public EventLogDto getEventByEventId(@NotNull(message = "{event.id.nonnull}") UUID id) {
		EventLog eventLog = eventLogService.findById(id)
			.orElseThrow(() -> new ServiceException("No event found by given id: " + id));
		return eventLogMapper.getEventDto(eventLog);
	}

	public List<EventLogDto> getEventDtoList() {
		List<EventLog> eventLogs = eventLogService.findByAll();
		return eventLogMapper.getEventDtoList(eventLogs);
	}

	public void saveEvent(@Valid EventLogDto eventLogDto, @NotNull(message = "{event.id.nonnull}") UUID id) {
		if (eventLogService.findById(id).isPresent()) {
			throw new ServiceException("Event already register with given id: " + id);
		}
		EventLog event = eventLogMapper.getEvent(eventLogDto);
		event.setEventId(id);
		log.info("Saved event with event id: {}", event.getEventId());
		eventLogService.save(event);
	}

	public void updateEvent(@ValidEnum(enumClass = EventProcessingStatus.class) @NotNull String status,
			@NotNull(message = "{event.id.nonnull}") UUID id, String errorMessage) {
		EventLog eventLog = eventLogService.findById(id)
			.orElseThrow(() -> new ServiceException("Event already register with given id: " + id));
		eventLog.setProcessingStatus(EventProcessingStatus.valueOf(status));
		if (errorMessage != null) {
			eventLog.setErrorMessage(errorMessage);
		}
		log.info("Update event with event id: {} to {}", eventLog.getEventId(), status);
		eventLogService.save(eventLog);
	}

}
