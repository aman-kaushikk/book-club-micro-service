package org.loop.troop.club.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@DiscriminatorValue("YES_NO")
@Getter
public class YesNoPoll extends Poll {

	@ElementCollection
	@CollectionTable(name = "polled_books_yes_no", joinColumns = @JoinColumn(name = "poll_id_fk"))
	@Column(name = "polled_book_id")
	private final List<UUID> bookIds = new ArrayList<>();

	@Override
	public void addBookSelection(UUID bookId) {
		if (bookIds.isEmpty()) {
			bookIds.add(bookId); // Only one book can be selected
		}
	}

}
