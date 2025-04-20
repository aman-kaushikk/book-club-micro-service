package org.loop.troop.book.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * The interface Book repository.
 */
@Repository
interface BookRepository extends JpaRepository<Book, UUID> {
    boolean existsByTitle(String title);

    @Query("SELECT bl FROM Book b JOIN b.buyLinks bl")
    List<BuyLink> getAllBuyLink();
}
