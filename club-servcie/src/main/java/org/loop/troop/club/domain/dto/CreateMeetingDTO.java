package org.loop.troop.club.domain.dto;

import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
public class CreateMeetingDTO {
    private String title;
    private LocalDate meetingDate;
    private LocalTime meetingTime;
    private String timeZone;
    private Integer durationMinutes;
    private String virtualMeetingLink;
    private String location;
    private String note;
    private Integer rsvpLimit;
    private boolean hostVideoEnabled;
    private List<String> materials;
}