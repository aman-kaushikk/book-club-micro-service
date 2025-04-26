package org.loop.troop.book.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;
import org.loop.troop.book.domain.enums.BookStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * The type Book.
 *
 * @author alex
 */
@Entity
@Table(name = "book")
@Getter
@Setter
@NoArgsConstructor
@DynamicUpdate
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

	private Double rating;

	private Integer pageCount;

	private Integer bookmarked;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private BookStatus bookStatus;

	@ElementCollection
	@CollectionTable(name = "book_buy_link", joinColumns = @JoinColumn(name = "book_id_fk"))
	@Column(name = "buy_link")
	private List<BuyLink> buyLinks = new ArrayList<>();

	@ElementCollection
	@CollectionTable(name = "book_club", joinColumns = @JoinColumn(name = "book_id_fk"))
	@Column(name = "club_id")
	private List<UUID> clubIds = new ArrayList<>();

	@ElementCollection
	@CollectionTable(name = "book_genre", joinColumns = @JoinColumn(name = "book_id_fk"))
	@Column(name = "genre_id")
	private List<String> genres = new ArrayList<>();

	@ElementCollection
	@CollectionTable(name = "book_tag", joinColumns = @JoinColumn(name = "book_id_fk"))
	@Column(name = "tag_id")
	private List<String> tags = new ArrayList<>();

	@OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Review> reviews = new ArrayList<>();

}
