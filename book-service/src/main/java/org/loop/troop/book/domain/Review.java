package org.loop.troop.book.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * The type Review.
 *
 * @author alex
 */
@Entity
@Table(name = "review")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
class Review {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;

	@Column(nullable = false)
	private UUID userId;

	@Column(nullable = false)
	private String userProfileUrl;

	@Column(columnDefinition = "TEXT", nullable = false)
	private String reviewDescription;

	@Column(nullable = false)
	private String reviewTitle;

	@Column(nullable = false)
	private Double star;

	private Boolean inappropriate = false;

	private LocalDateTime createdAt = LocalDateTime.now();

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "book_id_fk", nullable = false)
	private Book book;

}
