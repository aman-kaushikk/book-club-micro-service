package org.loop.troop.club.domain.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

@Data
public class CreateClubRequest {

	@NotBlank(message = "{club.name.required}")
	private String name;

	@URL(message = "{club.profileUrl.url}")
	private String profileUrl;

	@NotBlank(message = "{club.description.required}")
	@Length(max = 500)
	private String description;

	@NotBlank(message = "{club.about-us.required}")
	private String aboutUs;

}
