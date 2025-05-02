package org.loop.troop.club.domain;

import jakarta.persistence.*;

@Entity
public class Meeting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String topic;
    private String scheduledDate;

    @ManyToOne
    @JoinColumn(name = "book_club_id")
    private Club club;

    // Constructors, Getters, and Setters

    public Meeting(String topic, String scheduledDate, Club club) {
        this.topic = topic;
        this.scheduledDate = scheduledDate;
        this.club = club;
    }
}