package org.loop.troop.book.domain.modal;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * The type Review dto.
 *
 * @author alex
 */
@Data
public class ReviewDto implements Serializable {

	private UUID id;

	private UUID userId;

	private String userProfileUrl;

	private String reviewDescription;

	private boolean inappropriate;

	private Double star;

	private LocalDateTime createdAt;

}
