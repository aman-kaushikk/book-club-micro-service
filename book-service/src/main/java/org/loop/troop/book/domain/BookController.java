package org.loop.troop.book.domain;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import org.loop.troop.book.domain.modal.BookDto;
import org.loop.troop.book.domain.modal.BookRequest;
import org.loop.troop.book.domain.modal.PageDto;
import org.loop.troop.book.domain.enums.Vendor;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

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

}
