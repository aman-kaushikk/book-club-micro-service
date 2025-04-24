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
	 * To dto book list.
	 * @param books the books
	 * @return the list
	 */
	List<BookDto> toDtoBookList(List<Book> books);

	/**
	 * To entity book list.
	 * @param bookDtoList the book dto list
	 * @return the list
	 */
	List<Book> toEntityBookList(List<BookDto> bookDtoList);

	/**
	 * To dto book dto.
	 * @param book the book
	 * @return the book dto
	 */
	BookDto toBookDto(Book book);

	/**
	 * To entity book.
	 * @param bookDto the book dto
	 * @return the book
	 */
	@Mapping(target = "rating", ignore = true)
	Book toBookEntity(BookDto bookDto);

	/**
	 * To dto buy link dto.
	 * @param buyLink the buy link
	 * @return the buy link dto
	 */
	BuyLinkDto toBuyLinkDto(BuyLink buyLink);

	/**
	 * To entity buy link.
	 * @param dto the dto
	 * @return the buy link
	 */
	BuyLink toBuyLinkEntity(BuyLinkDto dto);

	/**
	 * To dto review dto.
	 * @param review the review
	 * @return the review dto
	 */
	ReviewDto toReviewDtoDto(Review review);

	/**
	 * To entity review.
	 * @param dto the dto
	 * @return the review
	 */
	@Mapping(target = "book", ignore = true)
	Review toReviewEntity(ReviewDto dto);

	/**
	 * To dto buy link list.
	 * @param buyLinks the buy links
	 * @return the list
	 */
	List<BuyLinkDto> toDtoBuyLinkList(List<BuyLink> buyLinks);

	/**
	 * To entity buy link list.
	 * @param buyLinks the buy links
	 * @return the list
	 */
	List<BuyLink> toEntityBuyLinkList(List<BuyLinkDto> buyLinks);

	/**
	 * To dto review list.
	 * @param reviews the reviews
	 * @return the list
	 */
	List<ReviewDto> toDtoReviewList(List<Review> reviews);

	/**
	 * To entity review list.
	 * @param reviews the reviews
	 * @return the list
	 */
	List<Review> toEntityReviewList(List<ReviewDto> reviews);

	/**
	 * Calculate entity rating.
	 * @param bookdto the bookdto
	 * @param book the book
	 */
	@AfterMapping
	default void calculateEntityRating(BookDto bookdto, @MappingTarget Book book) {
		List<ReviewDto> reviews = bookdto.getReviews();
		if (reviews != null && !reviews.isEmpty()) {
			double avg = reviews.stream().mapToDouble(ReviewDto::getStar).average().orElse(0.0);
			book.setRating(Math.min(avg, 5.0));
		}
		else {
			book.setRating(0.0);
		}
	}

}
