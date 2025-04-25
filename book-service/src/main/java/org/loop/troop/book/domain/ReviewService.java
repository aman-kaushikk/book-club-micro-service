package org.loop.troop.book.domain;

import org.loop.troop.book.domain.modal.CreateNewReview;
import org.loop.troop.book.domain.modal.PageDto;
import org.loop.troop.book.domain.modal.ReviewDto;

import java.util.UUID;

/**
 * The interface Review service.
 */
public interface ReviewService {

	/**
	 * Create new review.
	 * @param createNewReview the create new review
	 */
	void createNewReview(CreateNewReview createNewReview);

	/**
	 * Gets reviews.
	 * @param bookId the book id
	 * @param page the page
	 * @param size the size
	 * @param sortBy the sort by
	 * @param sortDirection the sort direction
	 * @return the reviews
	 */
	PageDto<ReviewDto> getReviews(UUID bookId, int page, int size, String sortBy, String sortDirection);

}
