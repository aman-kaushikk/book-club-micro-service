package org.loop.troop.book.domain.modal;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.loop.troop.book.domain.enums.Vendor;
import org.loop.troop.book.web.validator.ValidEnum;

import java.util.Objects;

/**
 * The type Buy link dto.
 *
 * @author alex
 */
@Getter
@Setter
@ToString
public class BuyLinkDto {

	@NotBlank(message = "{book.link.url.missing}")
	private String url;

	@NotBlank(message = "{book.link.vendor.missing}")
	@ValidEnum(enumClass = Vendor.class, message = "{book.vendor.invalid}")
	private String vendor;

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		BuyLinkDto buyLinkdto = (BuyLinkDto) o;
		return Objects.equals(vendor, buyLinkdto.vendor) && Objects.equals(url, buyLinkdto.url);
	}

	@Override
	public int hashCode() {
		return Objects.hash(vendor, url);
	}

}
