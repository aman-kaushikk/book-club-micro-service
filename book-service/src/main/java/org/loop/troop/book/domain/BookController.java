package org.loop.troop.book.domain;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import org.loop.troop.book.domain.enums.Vendor;
import org.loop.troop.book.domain.modal.*;
import org.loop.troop.book.domain.request.buylinks.*;
import org.loop.troop.book.domain.request.club.*;
import org.loop.troop.book.domain.request.genre.*;
import org.loop.troop.book.domain.request.tag.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.*;

/**
 * The type Book controller.
 */
@RestController
@RequestMapping("/api/v1/book")
@RequiredArgsConstructor
@Validated
class BookController {

	private final BookService bookService;

	private final BookQueueProducer bookQueueProducer;

	/**
	 * Register new book response entity.
	 * @param bookRequest the book request
	 * @return the response entity
	 */
	@PostMapping("/register")
	ResponseEntity<String> registerNewBook(@RequestBody @Valid BookRequest bookRequest) {
		UUID uuid = UUID.randomUUID();
		bookQueueProducer.sendBookCreatedMessage(bookRequest, uuid);
		return ResponseEntity.status(CREATED).body(uuid.toString());
	}

	/**
	 * Register manually new book response entity.
	 * @param bookRequest the book request
	 * @return the response entity
	 */
	@PostMapping("/manually/register")
	ResponseEntity<BookDto> registerManuallyNewBook(@RequestBody @Valid BookRequest bookRequest) {
		var registerBook = bookService.register(bookRequest.getUrl(), Vendor.valueOf(bookRequest.getVendor()));
		return ResponseEntity.status(CREATED).body(registerBook);
	}

	/**
	 * Gets paged book dto.
	 * @param page the page
	 * @param size the size
	 * @param sortBy the sort by
	 * @param sortDirection the sort direction
	 * @return the paged book dto
	 */
	@GetMapping
	ResponseEntity<PageDto<BookDto>> getBooksWithPaging(@PositiveOrZero @RequestParam(defaultValue = "0") int page,
			@Positive @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "bookId") String sortBy,
			@RequestParam(defaultValue = "ASC") String sortDirection) {
		var allBook = bookService.getAllBook(page, size, sortBy, sortDirection);
		return ResponseEntity.status(OK).body(allBook);
	}

	@RequestMapping("/update/event")
	public ResponseEntity<Void> updateBook(@RequestParam("message") String message) {
		bookQueueProducer.sendBookUpdatedMessage(message);
		return ResponseEntity.ok().build();
	}

	// -------------------------------------------------- Additional Endpoints
	// -----------------------------------

	// Add Buy Link
	@PostMapping("/{bookId}/add-buy-link")
	public ResponseEntity<GenericResponseDto> addBuyLink(@PathVariable UUID bookId,
			@Valid @NotEmpty(message = "BuyLinks cannot be empty") @RequestBody List<BuyLinkDto> buyLinks) {
		AddBuyLinkRequest request = RequestFactory.createAddBuyLinkRequest(buyLinks);
		RowEffected rowEffected = bookService.addBuyLinks(bookId, request);
		return buildResponse(CREATED, "Buy Links added successfully", bookId, rowEffected);
	}

	// Remove Buy Link
	@DeleteMapping("/{bookId}/remove-buy-link")
	public ResponseEntity<GenericResponseDto> removeBuyLink(@PathVariable UUID bookId,
			@Valid @NotEmpty(message = "BuyLinks cannot be empty") @RequestBody List<BuyLinkDto> buyLinks) {
		RemoveBuyLinkRequest request = RequestFactory.createRemoveBuyLinkRequest(buyLinks);
		RowEffected rowEffected = bookService.removeBuyLinks(bookId, request);
		return buildResponse(ACCEPTED, "Buy Links removed successfully", bookId, rowEffected);
	}

	// Add Club ID
	@PostMapping("/{bookId}/add-club")
	public ResponseEntity<GenericResponseDto> addClub(@PathVariable UUID bookId,
			@NotEmpty(message = "ClubIds cannot be empty") @RequestBody List<UUID> clubIds) {
		AddClubIdRequest request = RequestFactory.createAddClubIdRequest(clubIds);
		RowEffected rowEffected = bookService.addClubs(bookId, request);
		return buildResponse(CREATED, "Club IDs added successfully", bookId, rowEffected);
	}

	// Remove Club ID
	@DeleteMapping("/{bookId}/remove-club")
	public ResponseEntity<GenericResponseDto> removeClub(@PathVariable UUID bookId,
			@NotEmpty(message = "ClubIds cannot be empty") @RequestBody List<UUID> clubIds) {
		RemoveClubIdRequest request = RequestFactory.createRemoveClubIdRequest(clubIds);
		RowEffected rowEffected = bookService.removeClubs(bookId, request);
		return buildResponse(ACCEPTED, "Club IDs removed successfully", bookId, rowEffected);
	}

	// Add Genre
	@PostMapping("/{bookId}/add-genre")
	public ResponseEntity<GenericResponseDto> addGenre(@PathVariable UUID bookId,
			@NotEmpty(message = "Genres cannot be empty") @RequestBody List<String> genres) {
		AddGenreRequest request = RequestFactory.createAddGenreRequest(genres);
		RowEffected rowEffected = bookService.addGenres(bookId, request);
		return buildResponse(CREATED, "Genres added successfully", bookId, rowEffected);
	}

	// Remove Genre
	@PostMapping("/{bookId}/remove-genre")
	public ResponseEntity<GenericResponseDto> removeGenre(@PathVariable UUID bookId,
			@NotEmpty(message = "Genres cannot be empty") @RequestBody List<String> genres) {
		RemoveGenreRequest request = RequestFactory.createRemoveGenreRequest(genres);
		RowEffected rowEffected = bookService.removeGenres(bookId, request);
		return buildResponse(ACCEPTED, "Genres removed successfully", bookId, rowEffected);
	}

	// Add Tag
	@PostMapping("/{bookId}/add-tag")
	public ResponseEntity<GenericResponseDto> addTag(@PathVariable UUID bookId,
			@NotEmpty(message = "Tags cannot be empty") @RequestBody List<String> tags) {
		AddTagRequest request = RequestFactory.createAddTagRequest(tags);
		RowEffected rowEffected = bookService.addTags(bookId, request);
		return buildResponse(CREATED, "Tags added successfully", bookId, rowEffected);
	}

	// Remove Tag
	@DeleteMapping("/{bookId}/remove-tag")
	public ResponseEntity<GenericResponseDto> removeTag(@PathVariable UUID bookId,
			@NotEmpty(message = "Tags cannot be empty") @RequestBody List<String> tags) {
		RemoveTagRequest request = RequestFactory.createRemoveTagRequest(tags);
		RowEffected rowEffected = bookService.removeTags(bookId, request);
		return buildResponse(ACCEPTED, "Tags removed successfully", bookId, rowEffected);
	}

	// Helper method for building response
	private ResponseEntity<GenericResponseDto> buildResponse(HttpStatus status, String message, UUID bookId,
			RowEffected rowEffected) {
		GenericResponseDto response = new GenericResponseDto(status, message, "/api/books/" + bookId);
		response.setData(rowEffected.getRows());
		response.setRowCount(rowEffected.getRowCount());
		return ResponseEntity.status(status).body(response);
	}

}
