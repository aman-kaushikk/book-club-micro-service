package org.loop.troop.club.domain;


import org.loop.troop.club.domain.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface MeetingRepository extends JpaRepository<Meeting, UUID> {
    Optional<Meeting> findByClub_ClubIdAndId(UUID clubId, UUID meetingId);
}