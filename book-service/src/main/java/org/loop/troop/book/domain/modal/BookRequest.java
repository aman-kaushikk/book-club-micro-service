package org.loop.troop.book.domain.modal;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.loop.troop.book.domain.enums.Vendor;
import org.loop.troop.book.web.validator.ValidEnum;

/**
 * The type Book request.
 */
@Data
public class BookRequest {

	@NotBlank(message = "{book.url.missing}")
	private String url;

	@ValidEnum(enumClass = Vendor.class, message = "{book.vendor.invalid}")
	@NotBlank(message = "book.vendor.missing")
	private String vendor;

}
