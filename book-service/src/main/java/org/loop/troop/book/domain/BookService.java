package org.loop.troop.book.domain;

import org.loop.troop.book.domain.modal.BookDto;
import org.loop.troop.book.domain.modal.BuyLinkDto;
import org.loop.troop.book.domain.modal.PageDto;
import org.loop.troop.book.domain.enums.Vendor;
import org.loop.troop.book.domain.modal.RowEffected;
import org.loop.troop.book.domain.request.buylinks.*;
import org.loop.troop.book.domain.request.club.*;
import org.loop.troop.book.domain.request.genre.*;
import org.loop.troop.book.domain.request.tag.*;
import java.util.List;
import java.util.UUID;

/**
 * The interface Book service.
 *
 * @author alex
 */
public interface BookService {

	/**
	 * Register book dto.
	 * @param url the url
	 * @param vendor the vendor
	 * @return the book dto
	 */
	BookDto register(String url, Vendor vendor);

	/**
	 * Gets all buy link.
	 * @return the all buy link
	 */
	List<BuyLinkDto> getAllBuyLink();

	/**
	 * Gets all book.
	 * @param page the page
	 * @param size the size default size 10
	 * @param sortBy the sort by
	 * @param sortDirection the sort direction
	 * @return the all book
	 */
	PageDto<BookDto> getAllBook(int page, int size, String sortBy, String sortDirection);

	// --------------------------- Additional methods
	// -----------------------------------------------

	// Add Buy Links
	RowEffected addBuyLinks(UUID bookId, AddBuyLinkRequest request);

	// Remove Buy Links
	RowEffected removeBuyLinks(UUID bookId, RemoveBuyLinkRequest request);

	// Add Club IDs
	RowEffected addClubs(UUID bookId, AddClubIdRequest request);

	// Remove Club IDs
	RowEffected removeClubs(UUID bookId, RemoveClubIdRequest request);

	// Add Genres
	RowEffected addGenres(UUID bookId, AddGenreRequest request);

	// Remove Genres
	RowEffected removeGenres(UUID bookId, RemoveGenreRequest request);

	// Add Tags
	RowEffected addTags(UUID bookId, AddTagRequest request);

	// Remove Tags
	RowEffected removeTags(UUID bookId, RemoveTagRequest request);

}
