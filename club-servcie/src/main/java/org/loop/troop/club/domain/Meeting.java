package org.loop.troop.club.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
class Meeting {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String topic;

	private String scheduledDate;

	@ManyToOne
	@JoinColumn(name = "book_club_id")
	private Club club;

	public Meeting(String topic, String scheduledDate, Club club) {
		this.topic = topic;
		this.scheduledDate = scheduledDate;
		this.club = club;
	}

}