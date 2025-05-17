package org.loop.troop.club.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
interface ClubRepository extends JpaRepository<Club, UUID> {

	@Query("select c from Club c inner join c.polls polls where c.clubId = ?1 and polls.pollId = ?2")
	Optional<Poll> getPollByClubIdAndPollId(UUID clubId, UUID pollId);

}
