package org.loop.troop.book.domain.modal;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
import java.util.UUID;

/**
 * The type Book dto.
 *
 * @author alex
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BookDto extends BaseDto {

	private UUID bookId;

	private String title;

	private String url;

	private String author;

	private String description;

	private List<BuyLinkDto> buyLinks;

	private List<ReviewDto> reviews;

	private List<UUID> clubIds;

	private Double rating;

	private Integer pageCount;

}
