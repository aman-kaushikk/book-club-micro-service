package org.loop.troop.event.domain;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.loop.troop.event.domain.modal.EventLogDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.UUID;

@Service
@Validated
@RequiredArgsConstructor
@Slf4j
public class EventService {

	private final EventLogRepository eventLogRepository;

	private final EventLogMapper eventLogMapper;

	public EventLogDto getEventByEventId(@NotNull(message = "{event.id.nonnull}") UUID id) {
		EventLog eventLog = eventLogRepository.findById(id)
			.orElseThrow(() -> new ServiceException("No event found by given id: " + id));
		return eventLogMapper.getEventDto(eventLog);
	}

	@Transactional
	public void saveEvent(@Valid EventLogDto eventLogDto, @NotNull(message = "{event.id.nonnull}") UUID id) {
        if(eventLogRepository.findById(id).isPresent()){
            throw new ServiceException("Event already register with given id: " + id);
        }
        EventLog event = eventLogMapper.getEvent(eventLogDto);
		event.setEventId(id);
		log.info("Saved event with event id: {}", event.getEventId());
		eventLogRepository.save(event);
	}

}
