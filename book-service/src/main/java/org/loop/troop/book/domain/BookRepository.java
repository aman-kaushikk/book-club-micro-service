package org.loop.troop.book.domain;

import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * The interface Book repository.
 */
@Repository
interface BookRepository extends JpaRepository<Book, UUID> {

	/**
	 * Exists by title boolean.
	 * @param title the title
	 * @return the boolean
	 */
	boolean existsByTitle(String title);

	/**
	 * Gets all buy link.
	 * @return the all buy link
	 */
	@Query("SELECT bl FROM Book b JOIN b.buyLinks bl")
	List<BuyLink> getAllBuyLink();

	/**
	 * Find by book id page.
	 * @param bookId the book id
	 * @param pageable the pageable
	 * @return the page
	 */
	@Query("SELECT r FROM Review r WHERE r.book.bookId = :bookId")
	Page<Review> findByReviewByBookId(@Param("bookId") UUID bookId, Pageable pageable);

}
