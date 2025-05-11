package org.loop.troop.book.domain;

import org.loop.troop.book.domain.modal.EventLogDto;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;
import org.springframework.web.service.annotation.PutExchange;

import java.util.UUID;

@HttpExchange("/api/v1/event")
public interface BookServiceEventClient {

	@GetExchange("/{id}")
	EventLogDto getBookCreateEventById(@PathVariable("id") UUID id);

	@PostExchange("/register/{id}")
	void saveBookRegisterEvent(@PathVariable("id") UUID id, @RequestBody EventLogDto eventLogDto);

	@PutExchange("/update/{id}")
	void updateBookProcessingStatus(@PathVariable("id") UUID id, @RequestParam("status") String status,@RequestParam(name = "error-message",required = false) String errorMessage);

}
