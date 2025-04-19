package org.loop.troop.book.domain.modal;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
public class BookDto extends BaseDto {

	private UUID bookId;

	@NotBlank(message = "${title.missing}")
	private String title;

	@NotBlank(message = "${image.missing}")
	private String url;

	@NotBlank(message = "${author.missing}")
	private String author;

	@NotBlank(message = "${description.missing}")
	private String description;

	@NotEmpty(message = "At least one buy link is required")
	@Valid
	private List<BuyLinkDto> buyLinks = new ArrayList<>();

	@Valid
	private List<ReviewDto> reviews = new ArrayList<>();

	private List<UUID> clubIds = new ArrayList<>();

	private Double rating;

	@NotNull(message = "${pageCount.missing}")
	private Integer pageCount;

}
