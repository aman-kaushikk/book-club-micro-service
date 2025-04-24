package org.loop.troop.book.domain;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.loop.troop.book.domain.modal.CreateNewReview;
import org.loop.troop.book.domain.modal.PageDto;
import org.loop.troop.book.domain.modal.ReviewDto;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.UUID;

import static org.loop.troop.book.domain.Utility.getPageable;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class ReviewServiceImpl implements ReviewService {

	private final BookRepository bookRepository;

	private final BookMapper bookMapper;

	private final PageMapper<Review, ReviewDto> reviewPageMapper;

	@Override
	public void createNewReview(@Valid CreateNewReview createNewReview) {
		Assert.notNull(createNewReview, "create review object must not be null");
		ReviewDto reviewDto = createNewReview.toDto();
		Review reviewEntity = bookMapper.toReviewEntity(reviewDto);
		Book book = bookRepository.findById(createNewReview.getBookId())
			.orElseThrow(() -> new ServiceException("Cannot found book with given id: " + createNewReview.getBookId()));
		reviewEntity.setBook(book);
		book.getReviews().add(reviewEntity);
		bookRepository.save(book);
	}

	@Override
	@Cacheable(value = "reviews", key = "#bookId + '-' + #page + '-' + #size + '-' + #sortBy + '-' + #sortDirection")
	public PageDto<ReviewDto> getReviews(UUID bookId, int page, int size, String sortBy, String sortDirection) {
		Pageable pageable = getPageable(page, size, sortBy, sortDirection);
		Page<Review> reviews = bookRepository.findByReviewByBookId(bookId, pageable);
		return reviewPageMapper.convertToDto(reviews, bookMapper::toDtoReviewList);
	}

}
