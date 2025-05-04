package org.loop.troop.club.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Member {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(columnDefinition = "UUID")
	private UUID id;

	@Column(nullable = false)
	private String name;

	@Column(unique = true)
	private String email;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "club_id")
	private Club club;

	// Optional: Add other fields as needed
	// private LocalDateTime joinedDate;
	// private String profilePictureUrl;

	public Member() {}

	public Member(String name, String email) {
		this.name = name;
		this.email = email;
	}
}