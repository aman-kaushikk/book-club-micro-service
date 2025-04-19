package org.loop.troop.book.domain;

import org.loop.troop.book.domain.modal.BookDto;
import org.loop.troop.book.domain.modal.BuyLinkDto;
import org.loop.troop.book.domain.modal.ReviewDto;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

/**
 * The interface Book mapper.
 */
@Mapper(componentModel = "spring")
interface BookMapper {

	/**
	 * To dto book dto.
	 * @param book the book
	 * @return the book dto
	 */
	BookDto toDto(Book book);

	/**
	 * To entity book.
	 * @param bookDto the book dto
	 * @return the book
	 */
	@Mapping(target = "target.rating", ignore = true)
	Book toEntity(BookDto bookDto);

	/**
	 * To dto buy link dto.
	 * @param buyLink the buy link
	 * @return the buy link dto
	 */
	BuyLinkDto toDto(BuyLink buyLink);

	/**
	 * To entity buy link.
	 * @param dto the dto
	 * @return the buy link
	 */
	BuyLink toEntity(BuyLinkDto dto);

	/**
	 * To dto review dto.
	 * @param review the review
	 * @return the review dto
	 */
	@Mapping(target = "target.book", ignore = true)
	ReviewDto toDto(Review review);

	/**
	 * To entity review.
	 * @param dto the dto
	 * @return the review
	 */
	Review toEntity(ReviewDto dto);

	/**
	 * To dto buy link list list.
	 * @param buyLinks the buy links
	 * @return the list
	 */
	List<BuyLinkDto> toDtoBuyLinkList(List<BuyLink> buyLinks);

	/**
	 * To entity buy link list list.
	 * @param buyLinks the buy links
	 * @return the list
	 */
	List<BuyLink> toEntityBuyLinkList(List<BuyLinkDto> buyLinks);

	/**
	 * To dto review list list.
	 * @param reviews the reviews
	 * @return the list
	 */
	@Mapping(target = "target.book", ignore = true)
	List<ReviewDto> toDtoReviewList(List<Review> reviews);

	/**
	 * To entity review list.
	 * @param reviews the reviews
	 * @return the list
	 */
	List<Review> toEntityReviewList(List<ReviewDto> reviews);

	/**
	 * Calculate rating.
	 * @param book the book
	 * @param bookDto the book dto
	 */
	@AfterMapping
	default void calculateRating(Book book, @MappingTarget BookDto bookDto) {
		List<Review> reviews = book.getReviews();
		if (reviews != null && !reviews.isEmpty()) {
			double avg = reviews.stream().mapToDouble(Review::getStar).average().orElse(0.0);
			bookDto.setRating(Math.min(avg, 5.0));
		}
		else {
			bookDto.setRating(0.0);
		}
	}

}
