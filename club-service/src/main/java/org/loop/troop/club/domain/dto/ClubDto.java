package org.loop.troop.club.domain.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class ClubDto {

	private UUID clubId;

	@NotBlank(message = "{club.name.required}")
	private String name;

	private String profileUrl;

	@NotBlank(message = "{club.description.required}")
	@Length(max = 500)
	private String description;

	@NotBlank(message = "{club.about-us.required}")
	private String aboutUs;

	private UUID currentReadingBook;

	private List<UUID> readBooks = new ArrayList<>();

	private List<UUID> futureReadBooks = new ArrayList<>();

	private List<ContactLinkInfoDto> contactLinkInfo = new ArrayList<>();

}
