package org.loop.troop.book.domain.modal;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.loop.troop.book.domain.BookStatus;
import org.loop.troop.book.web.validator.ValidEnum;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * The type Book dto.
 *
 * @author alex
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BookDto extends BaseDto implements Serializable {

	private UUID bookId;

	@NotBlank(message = "{book.title.missing}")
	private String title;

	@NotBlank(message = "{book.image-url.missing}")
	private String url;

	@NotBlank(message = "{book.author.missing}")
	private String author;

	@NotBlank(message = "{book.description.missing}")
	private String description;

	@NotEmpty(message = "{book.buy-link.min.length}")
	@Valid
	private List<BuyLinkDto> buyLinks = new ArrayList<>();

	@Valid
	private List<ReviewDto> reviews = new ArrayList<>();

	private List<UUID> clubIds = new ArrayList<>();

	private List<String> genres = new ArrayList<>();

	private List<String> tags = new ArrayList<>();

	@NotNull(message = "{book.rating.missing}")
	private Double rating;

	@NotNull(message = "{book.page-count.missing}")
	private Integer pageCount;

	@ValidEnum(enumClass = BookStatus.class, message = "{book.status.match}")
	@NotBlank(message = "{book.status.missing}")
	private String bookStatus;

	private Integer bookmarked;

}
