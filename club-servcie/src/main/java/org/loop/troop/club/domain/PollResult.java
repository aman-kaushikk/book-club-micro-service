package org.loop.troop.club.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "result_type", discriminatorType = DiscriminatorType.STRING)
@Getter
@Setter
abstract class PollResult {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne
    private Poll poll;

    public PollResult(Poll poll) {
        this.poll = poll;
    }

    public PollResult() {
    }

    public abstract Class<?> getExpectedVoteType();
}
