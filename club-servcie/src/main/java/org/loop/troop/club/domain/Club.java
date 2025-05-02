package org.loop.troop.club.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Club {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID clubId;

    private String name;

    @OneToMany(mappedBy = "club", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Member> members = new ArrayList<>();

    @OneToMany(mappedBy = "club", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Meeting> meetings = new ArrayList<>();

    @OneToMany(mappedBy = "club", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Poll> polls = new ArrayList<>();


    public void addMeeting(String topic, String scheduledDate) {
        Meeting meeting = new Meeting(topic, scheduledDate, this);
        meetings.add(meeting);
    }

    public void addPoll(String question, List<String> options) {
        Poll poll = new Poll(question, options, this);
        polls.add(poll);
    }

    public void removeMeeting(Meeting meeting) {
        meetings.remove(meeting);
    }

    public void removePoll(Poll poll) {
        polls.remove(poll);
    }
}

