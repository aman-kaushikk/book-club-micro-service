package org.loop.troop.club.domain;

import jakarta.persistence.*;

@Entity
public class Member {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	@ManyToOne
	@JoinColumn(name = "book_club_id")
	private Club club;

	// Getters and Setters

}
