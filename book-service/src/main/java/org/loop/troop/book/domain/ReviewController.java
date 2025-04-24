package org.loop.troop.book.domain;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import org.loop.troop.book.domain.modal.CreateNewReview;
import org.loop.troop.book.domain.modal.PageDto;
import org.loop.troop.book.domain.modal.ReviewDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/v1/review")
@RequiredArgsConstructor
public class ReviewController {

	private final ReviewService reviewService;

	@PostMapping("/add")
	ResponseEntity<Void> registerNewBook(@RequestBody @Valid CreateNewReview createNewReview) {
		reviewService.createNewReview(createNewReview);
		return ResponseEntity.ok().build();
	}

	@GetMapping
	ResponseEntity<PageDto<ReviewDto>> getReviewDto(@RequestParam UUID bookId, @PositiveOrZero @RequestParam int page,
			@Positive @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "star") String sortBy,
			@RequestParam(defaultValue = "ASC") String sortDirection) {
		var allBook = reviewService.getReviews(bookId, page, size, sortBy, sortDirection);
		return ResponseEntity.status(OK).body(allBook);
	}

}
