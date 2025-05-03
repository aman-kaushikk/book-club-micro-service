package org.loop.troop.club.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class ContactLinkInfo {

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String url;

}
