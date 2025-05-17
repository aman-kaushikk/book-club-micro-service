package org.loop.troop.book.domain.modal;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.loop.troop.book.domain.enums.EventProcessingStatus;
import org.loop.troop.book.domain.enums.EventType;
import org.loop.troop.book.web.validator.ValidEnum;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
public class EventLogDto {

	private UUID eventId;

	@NotBlank(message = "{event.app.missing}")
	private String app;

	@ValidEnum(enumClass = EventType.class, message = "{event.type.mismatch}")
	@NotBlank(message = "{event.type.missing}")
	private String eventType;

	@NotBlank(message = "{event.routing-key.missing}")
	private String routingKey;

	@NotBlank(message = "{event.exchange.missing}")
	private String exchange;

	@ValidEnum(enumClass = EventProcessingStatus.class, message = "{event.processing-status.mismatch}")
	@NotBlank(message = "{event.processing-status.missing}")
	private String processingStatus;

	private String errorMessage;

	private String payload;

	private LocalDateTime timestamp = LocalDateTime.now();

}