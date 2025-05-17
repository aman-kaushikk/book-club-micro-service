package org.loop.troop.book.domain;

import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

/**
 * The interface Book repository.
 */
@Repository
interface BookRepository extends JpaRepository<Book, UUID> {

	boolean existsByUrl(String url);

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

	/**
	 * Find buy link by book id and url and vendor list.
	 * @param bookId the book id
	 * @param urls the urls
	 * @param vendors the vendors
	 * @return the list
	 */
	@Query("SELECT bl FROM Book b JOIN b.buyLinks bl WHERE b.bookId = :bookId AND bl.url IN :urls AND bl.vendor IN :vendors")
	List<BuyLink> findBuyLinkByBookIdAndUrlAndVendor(@Param("bookId") UUID bookId, @Param("urls") Set<String> urls,
			@Param("vendors") Set<String> vendors);

}
