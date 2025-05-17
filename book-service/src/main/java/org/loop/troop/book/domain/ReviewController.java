package org.loop.troop.book.domain;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import org.loop.troop.book.domain.request.review.CreateNewReview;
import org.loop.troop.book.domain.modal.PageDto;
import org.loop.troop.book.domain.modal.ReviewDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static org.springframework.http.HttpStatus.OK;

/**
 * The type Review controller.
 */
@RestController
@RequestMapping("/api/v1/review")
@RequiredArgsConstructor
public class ReviewController {

	private final ReviewService reviewService;

	/**
	 * Register new book response entity.
	 * @param createNewReview the creation new review
	 * @return the response entity
	 */
	@PostMapping("/add")
	ResponseEntity<Void> registerNewBook(@RequestBody @Valid CreateNewReview createNewReview) {
		reviewService.createNewReview(createNewReview);
		return ResponseEntity.ok().build();
	}

	/**
	 * Gets review by book id.
	 * @param bookId the book id
	 * @param page the page
	 * @param size the size
	 * @param sortBy the sort by
	 * @param sortDirection the sort direction
	 * @return the review by book id
	 */
	@GetMapping
	ResponseEntity<PageDto<ReviewDto>> getReviewByBookId(@RequestParam UUID bookId,
			@PositiveOrZero @RequestParam(defaultValue = "0") int page,
			@Positive @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "star") String sortBy,
			@RequestParam(defaultValue = "ASC") String sortDirection) {
		var allBook = reviewService.getReviews(bookId, page, size, sortBy, sortDirection);
		return ResponseEntity.status(OK).body(allBook);
	}

}
