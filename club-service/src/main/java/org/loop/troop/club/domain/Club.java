package org.loop.troop.club.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
class Club {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(columnDefinition = "UUID")
	private UUID clubId;

	@Column(nullable = false)
	private String name;

	@Column(name = "url")
	private String profileUrl;

	@OneToMany(mappedBy = "club", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Member> members = new ArrayList<>();

	@ElementCollection
	@CollectionTable(name = "read_book", joinColumns = @JoinColumn(name = "club_id_fk"))
	@Column(name = "read_book_id")
	private List<UUID> readBooks = new ArrayList<>();

	@ElementCollection
	@CollectionTable(name = "future_read_book", joinColumns = @JoinColumn(name = "club_id_fk"))
	@Column(name = "future_read_book_id")
	private List<UUID> futureReadBooks = new ArrayList<>();

	@Column(name = "current_book")
	private UUID currentReadingBook;

	@Column(length = 500)
	private String description;

	@Column(columnDefinition = "TEXT")
	private String aboutUs;

	@ElementCollection
	@CollectionTable(name = "contact_link_info", joinColumns = @JoinColumn(name = "club_id_fk"),
			uniqueConstraints = @UniqueConstraint(columnNames = { "name", "url" }))
	@Column(name = "contact_link_id")
	private List<ContactLinkInfo> contactLinkInfo = new ArrayList<>();

	@OneToMany(mappedBy = "club", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Meeting> meetings = new ArrayList<>();

	@OneToMany(mappedBy = "club", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Poll> polls = new ArrayList<>();

	public void addMeeting(String title, LocalDate meetingDate, LocalTime meetingTime, String timeZone,
			int durationMinutes, String virtualMeetingLink, String location, String note, int rsvpLimit,
			boolean hostVideoEnabled, Member host, List<String> materials) {
		Meeting meeting = new Meeting(title, meetingDate, meetingTime, timeZone, durationMinutes, virtualMeetingLink,
				location, note, rsvpLimit, hostVideoEnabled, host, this, // Club reference
				materials);
		meetings.add(meeting);
	}

	public void addPoll(String question, List<String> options) {

	}

	public void removeMeeting(Meeting meeting) {
		meetings.remove(meeting);
	}

	public void removePoll(Poll poll) {
		polls.remove(poll);
	}

	public void addMember(Member member) {
		members.add(member);
		member.setClub(this);
	}

	public void removeMember(Member member) {
		members.remove(member);
		member.setClub(null);
	}

}
