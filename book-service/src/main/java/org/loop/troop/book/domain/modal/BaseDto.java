package org.loop.troop.book.domain.modal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * The type Base dto.
 *
 * @author alex
 */
@Data
public class BaseDto {

	@NotNull(message = "${creation.timestamp}")
	private LocalDateTime createdAt;

	@NotNull(message = "${created.by}")
	private UUID createdBy;

	private LocalDateTime updatedAt;

	private UUID updatedBy;

}
