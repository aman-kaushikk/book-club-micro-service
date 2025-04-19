package org.loop.troop.book.domain;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.loop.troop.book.domain.modal.BookDto;
import org.loop.troop.book.domain.modal.BookRequest;
import org.loop.troop.book.domain.service.Vendor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/book")
@RequiredArgsConstructor
class BookController {

    private final BookService bookService;

    @PostMapping("/register")
    ResponseEntity<BookDto> registerNewBook(@RequestBody @Valid BookRequest bookRequest){
        BookDto registerBook = bookService.register(bookRequest.getUrl(), Vendor.valueOf(bookRequest.getVendor()));
        return ResponseEntity.status(HttpStatus.CREATED).body(registerBook);
    }
}

