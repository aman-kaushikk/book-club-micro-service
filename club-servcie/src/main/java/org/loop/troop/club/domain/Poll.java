package org.loop.troop.club.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Poll {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String question;

	@ElementCollection
	private List<String> options;

	@ManyToOne
	@JoinColumn(name = "book_club_id")
	private Club club;

	public Poll(String question, List<String> options, Club club) {
		this.question = question;
		this.options = options;
		this.club = club;
	}

}