package org.loop.troop.book.domain.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.loop.troop.book.domain.ServiceException;

import java.util.UUID;

@RequiredArgsConstructor
@Slf4j
public abstract class BookRoutingStrategy {

	public abstract boolean supportRoutingKey(String key);

	public abstract String name();

	protected abstract boolean validatePayload(String payload);

	protected abstract void execute(String payload, UUID eventId);

	public final void process(String payload, UUID eventId) {
		if (!validatePayload(payload)) {
			throw new ServiceException("CANNOT PROCESS EVENT: Invalid payload");
		}
		execute(payload, eventId);
	}

}
