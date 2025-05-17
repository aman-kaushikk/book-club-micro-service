package org.loop.troop.event.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.loop.troop.event.domain.enums.EventProcessingStatus;
import org.loop.troop.event.domain.enums.EventType;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@Getter
@Setter
@NoArgsConstructor
@RedisHash("event_log")
public class EventLog implements Serializable {

	@Id
	private UUID eventId;

	@Indexed
	private String app;

	@Indexed
	private EventType eventType;

	@Indexed
	private String routingKey;

	@Indexed
	private String exchange;

	private String payload;

	@Indexed
	private EventProcessingStatus processingStatus;

	private String errorMessage;

	private LocalDateTime timestamp = LocalDateTime.now();

}