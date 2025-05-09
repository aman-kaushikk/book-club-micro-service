package org.loop.troop.event.domain;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.loop.troop.event.domain.modal.EventLogDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

	@PostMapping("/register/{id}")
	ResponseEntity<EventLogDto> saveEvent(@PathVariable("id") UUID eventId, @RequestBody EventLogDto eventLogDto) {
		log.info("Save for event id: {}", eventId);
		eventService.saveEvent(eventLogDto, eventId);
		EventLogDto savedEventDto = eventService.getEventByEventId(eventId);
		log.info("Saved event: {}", savedEventDto);
		return ResponseEntity.ok(savedEventDto);
	}

}
