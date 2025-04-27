package org.loop.troop.book.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

/**
 * The type Buy link.
 *
 * @author alex
 */
@Embeddable
@Getter
@Setter
@NoArgsConstructor
class BuyLink {

	@Column(name = "vendor", nullable = false)
	private String vendor;

	@Column(name = "url", nullable = false)
	private String url;

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		BuyLink buyLink = (BuyLink) o;
		return Objects.equals(vendor, buyLink.vendor) && Objects.equals(url, buyLink.url);
	}

	@Override
	public int hashCode() {
		return Objects.hash(vendor, url);
	}

}
