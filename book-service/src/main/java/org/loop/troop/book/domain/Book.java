package org.loop.troop.book.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * The type Book.
 *
 * @author alex
 */
@Entity
@Table(name = "books")
@Getter
@Setter
@NoArgsConstructor
class Book extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID bookId;

	@Column(nullable = false)
	private String title;

	@Column(name = "book_url", nullable = false)
	private String url;

	@Column(nullable = false)
	private String author;

	@Column(columnDefinition = "TEXT")
	private String description;

	@ElementCollection
	@CollectionTable(name = "book_buy_links", joinColumns = @JoinColumn(name = "book_id"))
	@Column(name = "buy_link")
	private List<BuyLink> buyLinks = new ArrayList<>();

	@ElementCollection
	@CollectionTable(name = "book_clubs", joinColumns = @JoinColumn(name = "book_id"))
	@Column(name = "club_id")
	private List<UUID> clubIds = new ArrayList<>();

	@OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Review> reviews = new ArrayList<>();

	private Double rating;

	private Integer pageCount;

}
