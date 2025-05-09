package org.loop.troop.club.domain;

import org.loop.troop.club.domain.dto.CreateMeetingDTO;
import org.loop.troop.club.domain.dto.MeetingDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.UUID;

@Service
public class MeetingService {

    private final MeetingRepository meetingRepository;
    private final ClubRepository clubRepository;
    private final MemberRepository memberRepository;
    private final MeetingMapper meetingMapper;

    public MeetingService(MeetingRepository meetingRepository,
                          ClubRepository clubRepository,
                          MemberRepository memberRepository,
                          MeetingMapper meetingMapper) {
        this.meetingRepository = meetingRepository;
        this.clubRepository = clubRepository;
        this.memberRepository = memberRepository;
        this.meetingMapper = meetingMapper;
    }

    @Transactional
    public MeetingDTO createMeeting(UUID clubId, UUID hostId, CreateMeetingDTO createMeetingDTO) {
        // Validate club existence
        Club club = clubRepository.findById(clubId)
                .orElseThrow(() -> new ResourceNotFoundException("Club not found"));

        // Validate host existence and membership
        Member host = memberRepository.findById(hostId)
                .orElseThrow(() -> new ResourceNotFoundException("Member not found"));

        if (!club.getMembers().contains(host)) {
            throw new IllegalArgumentException("Host must be a club member");
        }

        // Convert DTO to entity
//        Meeting meeting = meetingMapper.toEntity(createMeetingDTO);
        Meeting meeting = meetingMapper.toEntity(createMeetingDTO);

        // Set relationships
        meeting.setClub(club);
        meeting.setHost(host);

        // Save and return
        Meeting savedMeeting = meetingRepository.save(meeting);
        return meetingMapper.toDto(savedMeeting);
    }

    public MeetingDTO getMeeting(UUID clubId, UUID meetingId) {
        return meetingRepository.findByClub_ClubIdAndId(clubId, meetingId)
                .map(meetingMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Meeting not found"));
    }

    @Transactional
    public MeetingDTO updateMeeting(UUID clubId, UUID meetingId, MeetingDTO meetingDTO) {
        Meeting existingMeeting = meetingRepository.findByClub_ClubIdAndId(clubId, meetingId)
                .orElseThrow(() -> new ResourceNotFoundException("Meeting not found"));

        meetingMapper.updateMeetingFromDto(meetingDTO, existingMeeting);
        Meeting updatedMeeting = meetingRepository.save(existingMeeting);
        return meetingMapper.toDto(updatedMeeting);
    }

    @Transactional
    public void deleteMeeting(UUID clubId, UUID meetingId) {
        Meeting meeting = meetingRepository.findByClub_ClubIdAndId(clubId, meetingId)
                .orElseThrow(() -> new ResourceNotFoundException("Meeting not found"));
        meetingRepository.delete(meeting);
    }
}