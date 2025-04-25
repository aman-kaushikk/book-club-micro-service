package org.loop.troop.book.domain.modal;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import java.util.UUID;

/**
 * The type Create new review.
 */
@Data
public class CreateNewReview {

	@NotNull(message = "review.user-id.null")
	private UUID userId;

	@NotBlank(message = "review.user-profile.missing")
	@URL(message = "review.user-profile.url.valid")
	private String userProfileUrl;

	@NotBlank(message = "review.description.missing")
	private String reviewDescription;

	@NotNull(message = "review.star.null")
	@PositiveOrZero(message = "review.star.number")
	@Max(value = 5L, message = "review.start.limit")
	private Double star;

	@NotBlank(message = "${review.title.missing}")
	private String reviewTitle;

	@NotNull(message = "review.book-id.missing")
	private UUID bookId;

	/**
	 * To dto review dto.
	 * @return the review dto
	 */
	public ReviewDto toDto() {
		ReviewDto reviewDto = new ReviewDto();
		reviewDto.setUserId(this.userId);
		reviewDto.setUserProfileUrl(this.userProfileUrl);
		reviewDto.setReviewDescription(this.reviewDescription);
		reviewDto.setStar(this.star);
		return reviewDto;
	}

}
