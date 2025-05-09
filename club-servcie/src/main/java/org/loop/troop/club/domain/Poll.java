package org.loop.troop.club.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "poll_type", discriminatorType = DiscriminatorType.STRING)
abstract class Poll {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID pollId;

	private String title;

	private String note;

	@Embedded
	private DueSchedule dueSchedule;

	private boolean anonymous;

	@ElementCollection
	@CollectionTable(name = "poll_due_schedule", joinColumns = @JoinColumn(name = "poll_id_fk"))
	@Column(name = "polled_schedule_id")
	private List<DueSchedule> meetingSchedule = new ArrayList<>();

	@ManyToOne
	@JoinColumn(name = "book_club_id")
	private Club club;

	@Transient
	private String pollType;

	@ElementCollection
	@CollectionTable(name = "polled_books", joinColumns = @JoinColumn(name = "poll_id_fk"))
	@Column(name = "polled_book_id")
	protected final List<UUID> bookIds = new ArrayList<>();

	@PostLoad
	@PostPersist
	@PostUpdate
	private void setDiscriminatorType() {
		this.pollType = this.getClass().getAnnotation(DiscriminatorValue.class).value();
	}

	@OneToOne
	PollResult pollResult;

	public abstract void addBookSelection(UUID bookId);

}