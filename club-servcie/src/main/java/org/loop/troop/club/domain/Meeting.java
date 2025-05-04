package org.loop.troop.club.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Meeting {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(columnDefinition = "UUID")
	private UUID id;
	private String title;  // Changed from 'topic' to match DTO
	private LocalDate meetingDate;
	private LocalTime meetingTime;
	private String timeZone;
	private Integer durationMinutes;
	private String virtualMeetingLink;
	private String location;
	private String note;
	private Integer rsvpLimit;
	private boolean hostVideoEnabled;

	@ManyToOne
	@JoinColumn(name = "host_id" )
	private Member host;

	@ManyToOne
	@JoinColumn(name = "club_id")
	private Club club;

	@ElementCollection
	@CollectionTable(name = "meeting_materials", joinColumns = @JoinColumn(name = "meeting_id"))
	@Column(name = "material_link")
	private List<String> materials = new ArrayList<>();

	@ManyToMany
	@JoinTable(
			name = "meeting_attendees",
			joinColumns = @JoinColumn(name = "meeting_id"),
			inverseJoinColumns = @JoinColumn(name = "member_id")
	)
	private List<Member> attendees = new ArrayList<>();

	// Updated constructor to match DTO fields
	public Meeting(String title, LocalDate meetingDate, LocalTime meetingTime,
				   String timeZone, Integer durationMinutes, String virtualMeetingLink,
				   String location, String note, Integer rsvpLimit,
				   boolean hostVideoEnabled, Member host, Club club,
				   List<String> materials) {
		this.title = title;
		this.meetingDate = meetingDate;
		this.meetingTime = meetingTime;
		this.timeZone = timeZone;
		this.durationMinutes = durationMinutes;
		this.virtualMeetingLink = virtualMeetingLink;
		this.location = location;
		this.note = note;
		this.rsvpLimit = rsvpLimit;
		this.hostVideoEnabled = hostVideoEnabled;
		this.host = host;
		this.club = club;
		this.materials = materials != null ? materials : new ArrayList<>();
	}
}