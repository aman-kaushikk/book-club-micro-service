package org.loop.troop.book.domain.modal;

import jakarta.validation.constraints.NotBlank;
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

	@NotBlank(message = "Creation timestamp is mandatory")
	private LocalDateTime createdAt;
	@NotBlank(message = "Created by is mandatory")
	private UUID createdBy;
	private LocalDateTime updatedAt;
	private UUID updatedBy;

}
