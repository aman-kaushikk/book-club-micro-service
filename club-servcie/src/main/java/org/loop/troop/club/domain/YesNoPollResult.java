package org.loop.troop.club.domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue("YES_NO")
@Getter
@Setter
class YesNoPollResult extends PollResult implements Votable<Boolean> {

	private int yesCount;

	private int noCount;

	public YesNoPollResult() {
	}

	public YesNoPollResult(Poll poll) {
		super(poll);
	}

	@Override
	public void addVote(Boolean vote) {
		if (vote != null) {
			if (vote)
				yesCount++;
			else
				noCount++;
		}
	}

	@Override
	public Class<?> getExpectedVoteType() {
		return Boolean.class;
	}

}
