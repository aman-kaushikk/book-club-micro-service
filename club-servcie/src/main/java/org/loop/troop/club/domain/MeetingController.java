package org.loop.troop.club.domain;


import org.loop.troop.club.domain.dto.CreateMeetingDTO;
import org.loop.troop.club.domain.dto.MeetingDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
@RequestMapping("/api/clubs/{clubId}/meetings")
public class MeetingController {

    private final MeetingService meetingService;

    public MeetingController(MeetingService meetingService) {
        this.meetingService = meetingService;
    }

    @PostMapping
    public ResponseEntity<MeetingDTO> createMeeting(
            @PathVariable UUID clubId,
            @RequestParam UUID hostId,
            @RequestBody CreateMeetingDTO createMeetingDTO) {

        MeetingDTO createdMeeting = meetingService.createMeeting(clubId, hostId, createMeetingDTO);
        return new ResponseEntity<>(createdMeeting, HttpStatus.CREATED);
    }

    @GetMapping("/{meetingId}")
    public ResponseEntity<MeetingDTO> getMeeting(
            @PathVariable UUID clubId,
            @PathVariable UUID meetingId) {

        return ResponseEntity.ok(meetingService.getMeeting(clubId, meetingId));
    }

    @PutMapping("/{meetingId}")
    public ResponseEntity<MeetingDTO> updateMeeting(
            @PathVariable UUID clubId,
            @PathVariable UUID meetingId,
            @RequestBody MeetingDTO meetingDTO) {

        return ResponseEntity.ok(meetingService.updateMeeting(clubId, meetingId, meetingDTO));
    }

    @DeleteMapping("/{meetingId}")
    public ResponseEntity<Void> deleteMeeting(
            @PathVariable UUID clubId,
            @PathVariable UUID meetingId) {

        meetingService.deleteMeeting(clubId, meetingId);
        return ResponseEntity.noContent().build();
    }
}