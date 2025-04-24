package org.loop.troop.book.domain.modal;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import java.util.UUID;

@Data
public class CreateNewReview {

	@NotNull(message = "review.user-id.blank")
	private UUID userId;

	@NotBlank(message = "review.user-profile.blank")
	@URL(message = "review.user-profile.url")
	private String userProfileUrl;

	@NotBlank(message = "review.description.blank")
	private String reviewDescription;

	@NotNull(message = "review.star.blank")
	@PositiveOrZero(message = "review.star.number")
	@Max(value = 5L, message = "review.start.limit")
	private Double star;

	@NotNull(message = "review.book-id.blank")
	private UUID bookId;

	public ReviewDto toDto() {
		ReviewDto reviewDto = new ReviewDto();
		reviewDto.setUserId(this.userId);
		reviewDto.setUserProfileUrl(this.userProfileUrl);
		reviewDto.setReviewDescription(this.reviewDescription);
		reviewDto.setStar(this.star);
		return reviewDto;
	}

}
