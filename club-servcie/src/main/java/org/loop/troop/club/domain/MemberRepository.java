package org.loop.troop.club.domain;

import org.loop.troop.club.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface MemberRepository extends JpaRepository<Member, UUID> {

	// Custom query methods
	boolean existsByEmail(String email);

	Member findByEmail(String email);

	Optional<Member> findByClub_ClubIdAndId(UUID clubId, UUID memberId);

}
