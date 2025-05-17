package org.loop.troop.event.domain;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.loop.troop.event.domain.enums.EventProcessingStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Slf4j
@RequiredArgsConstructor
public class EventLogService {

	private final EventLogRepository eventLogRepository;

	public List<EventLog> getPendingEvent() {
		return eventLogRepository.findByProcessingStatus(EventProcessingStatus.PENDING);
	}

	public List<EventLog> getCompletedEvent() {
		return eventLogRepository.findByProcessingStatus(EventProcessingStatus.COMPLETED);
	}

	public void deleteEvent(List<EventLog> eventLogs) {
		eventLogRepository.deleteAll(eventLogs);
	}

	public Optional<EventLog> findById(UUID uuid) {
		return eventLogRepository.findById(uuid);
	}

	public List<EventLog> findByAll() {
		Iterable<EventLog> eventLogIterable = eventLogRepository.findAll();
		return StreamSupport.stream(eventLogIterable.spliterator(), false).collect(Collectors.toList());
	}

	public void save(EventLog eventLog) {
		eventLogRepository.save(eventLog);
	}

}
