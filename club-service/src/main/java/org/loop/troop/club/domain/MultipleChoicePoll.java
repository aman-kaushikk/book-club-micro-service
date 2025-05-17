package org.loop.troop.club.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@DiscriminatorValue("MULTIPLE_CHOICE")
@Getter
public class MultipleChoicePoll extends Poll {

	@ElementCollection
	@CollectionTable(name = "polled_books_multiple_choice", joinColumns = @JoinColumn(name = "poll_id_fk"))
	@Column(name = "polled_book_id")
	private final List<UUID> bookIds = new ArrayList<>();

	@Override
	public void addBookSelection(UUID bookId) {
		bookIds.add(bookId);
	}

}
