package org.loop.troop.book.domain.modal;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
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

	@NotNull(message = "${review.user-id.null}")
	private UUID userId;

	@NotBlank(message = "${review.user-profile.missing}")
	private String userProfileUrl;

	@NotBlank(message = "${review.description.missing}")
	private String reviewDescription;

	@NotBlank(message = "${review.title.missing}")
	private String reviewTitle;

	private boolean inappropriate;

	@NotNull(message = "${review.star.null}")
	@PositiveOrZero(message = "${review.star.number}")
	@Max(value = 5L, message = "${review.start.limit}")
	private Double star;

	@NotNull(message = "${review.created-at.null}")
	private LocalDateTime createdAt;

}
