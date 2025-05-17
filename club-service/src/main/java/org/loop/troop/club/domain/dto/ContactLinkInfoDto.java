package org.loop.troop.club.domain.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ContactLinkInfoDto {

	@NotBlank(message = "{contact.name.required}")
	private String name;

	@NotBlank(message = "{contact.url.required}")
	private String url;

	public ContactLinkInfoDto(String name, String url) {
		this.name = name;
		this.url = url;
	}

}
