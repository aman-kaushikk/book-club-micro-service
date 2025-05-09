package org.loop.troop.club.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


@Entity
@DiscriminatorValue("MULTIPLE_CHOICE")
@Getter
@Setter
class MultipleChoicePollResult extends PollResult implements Votable<UUID> {

    @ElementCollection
    @CollectionTable(name = "book_votes", joinColumns = @JoinColumn(name = "poll_result_id"))
    @MapKeyColumn(name = "book_id")
    @Column(name = "vote_count")
    private Map<UUID, Integer> bookVotes = new HashMap<>();

    protected MultipleChoicePollResult() {}

    public MultipleChoicePollResult(Poll poll) {
        super(poll);
    }

    @Override
    public void addVote(UUID bookId) {
        bookVotes.put(bookId, bookVotes.getOrDefault(bookId, 0) + 1);
    }

    @Override
    public Class<?> getExpectedVoteType() {
        return UUID.class;
    }
}
