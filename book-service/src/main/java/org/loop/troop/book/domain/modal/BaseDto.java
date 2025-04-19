package org.loop.troop.book.domain.modal;

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

	private LocalDateTime createdAt;

	private LocalDateTime updatedAt;

	private UUID createdBy;

	private UUID updatedBy;

}
