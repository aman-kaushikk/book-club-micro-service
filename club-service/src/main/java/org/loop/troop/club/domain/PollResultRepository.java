package org.loop.troop.club.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

interface PollResultRepository extends JpaRepository<PollResult, UUID> {

	@Query("select p from PollResult p where p.poll.pollId = ?1")
	Optional<PollResult> findByPollResultByPollId(UUID pollId);

}