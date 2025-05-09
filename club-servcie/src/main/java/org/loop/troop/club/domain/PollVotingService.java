package org.loop.troop.club.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PollVotingService {

    private final ClubRepository clubRepository;
    private final PollResultRepository pollResultRepository;

    public <T> void vote(UUID clubId, UUID pollId, T vote, Class<T> voteType) {
        Poll poll = clubRepository.getPollByClubIdAndPollId(clubId,pollId)
                .orElseThrow(() -> new RuntimeException("Poll not found"));

        PollResult result = pollResultRepository.findByPollResultByPollId(pollId)
                .orElseGet(() -> {
                    PollResult newResult = createNewResult(poll);
                    return pollResultRepository.save(newResult);
                });

        if (!result.getExpectedVoteType().equals(voteType)) {
            throw new IllegalArgumentException(
                    "Expected vote type: " + result.getExpectedVoteType().getSimpleName() +
                    ", but got: " + voteType.getSimpleName()
            );
        }

        if (result instanceof Votable<?> votable) {
            @SuppressWarnings("unchecked")
            Votable<T> typedVotable = (Votable<T>) votable;
            typedVotable.addVote(vote);
            pollResultRepository.save(result);
        } else {
            throw new IllegalStateException("Poll result is not votable");
        }
    }

    private PollResult createNewResult(Poll poll) {
        if (poll instanceof MultipleChoicePoll) {
            return new MultipleChoicePollResult(poll);
        } else if (poll instanceof YesNoPoll) {
            return new YesNoPollResult(poll);
        }
        throw new IllegalArgumentException("Unsupported poll type: " + poll.getClass().getSimpleName());
    }
}
