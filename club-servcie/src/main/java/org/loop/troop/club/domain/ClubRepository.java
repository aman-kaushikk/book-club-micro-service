package org.loop.troop.club.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
interface ClubRepository extends JpaRepository<Club, UUID> {

}
