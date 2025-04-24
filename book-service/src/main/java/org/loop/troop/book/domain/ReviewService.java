package org.loop.troop.book.domain;

import org.loop.troop.book.domain.modal.CreateNewReview;
import org.loop.troop.book.domain.modal.PageDto;
import org.loop.troop.book.domain.modal.ReviewDto;

import java.util.UUID;

public interface ReviewService {

	void createNewReview(CreateNewReview createNewReview);

	PageDto<ReviewDto> getReviews(UUID bookId, int page, int size, String sortBy, String sortDirection);

}
