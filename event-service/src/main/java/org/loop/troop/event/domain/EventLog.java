package org.loop.troop.event.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.loop.troop.event.domain.enums.EventProcessingStatus;
import org.loop.troop.event.domain.enums.EventType;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "event_log")
@Getter
@Setter
@NoArgsConstructor
class EventLog {

	@Id
	private UUID eventId;

	@Column(nullable = false)
	private String app;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private EventType eventType;

	@Column(nullable = false)
	private String routingKey;

	@Column(nullable = false)
	private String exchange;

	@Lob
	private String payload;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private EventProcessingStatus processingStatus;

	private String errorMessage;

	private LocalDateTime timestamp = LocalDateTime.now();

}
