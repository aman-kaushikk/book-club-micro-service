package org.loop.troop.book.domain.modal;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * The type Buy link dto.
 *
 * @author alex
 */
@Data
public class BuyLinkDto {
	@NotBlank(message = "${link.url.missing}")
	private String url;

	@NotBlank(message = "#{link.vendor.missing}")
	private String vendor;

}
