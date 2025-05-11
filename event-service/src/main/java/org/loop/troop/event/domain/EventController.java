package org.loop.troop.event.domain;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.loop.troop.event.domain.enums.EventProcessingStatus;
import org.loop.troop.event.domain.modal.EventLogDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/event")
@RequiredArgsConstructor
@Slf4j
public class EventController {

	private final EventService eventService;

	@GetMapping("/{id}")
	ResponseEntity<EventLogDto> getEventById(@PathVariable("id") UUID eventId) {
		log.info("Query for event id: {}", eventId);
		EventLogDto eventLogDto = eventService.getEventByEventId(eventId);
		log.info("Query response for event id: {}", eventLogDto);
		return ResponseEntity.ok(eventLogDto);
	}

	@GetMapping
	ResponseEntity<List<EventLogDto>> getAllEvent() {
		List<EventLogDto> events = eventService.getEventDtoList();
		return ResponseEntity.ok(events);
	}

	@PutMapping("/update/{id}")
	void updateEventStatus(@PathVariable("id") UUID eventId, @RequestParam("status") String status,@RequestParam(name = "error-message",required = false) String errorMessage) {
		eventService.updateEvent(status, eventId,errorMessage);
	}

	@PostMapping("/register/{id}")
	ResponseEntity<EventLogDto> saveEvent(@PathVariable("id") UUID eventId, @RequestBody EventLogDto eventLogDto) {
		log.info("Save for event id: {}", eventId);
		eventService.saveEvent(eventLogDto, eventId);
		EventLogDto savedEventDto = eventService.getEventByEventId(eventId);
		log.info("Saved event: {}", savedEventDto);
		return ResponseEntity.ok(savedEventDto);
	}

}
