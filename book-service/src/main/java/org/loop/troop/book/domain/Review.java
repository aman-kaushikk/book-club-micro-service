package org.loop.troop.book.domain;

import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

/**
 * The type Review.
 *
 * @author alex
 */
@Entity
@Table(name = "reviews")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
class Review {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;

	private UUID userId;

	private String userProfileUrl;

	@Column(columnDefinition = "TEXT")
	private String reviewDescription;

	private Double star;

	private Boolean inappropriate = false;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "book_id", nullable = false)
	private Book book;

}
