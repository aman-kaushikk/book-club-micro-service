package org.loop.troop.book.domain.request.book;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import org.loop.troop.book.domain.enums.BookStatus;
import org.loop.troop.book.web.validator.ValidEnum;

import java.util.UUID;

@Data
public class BookUpdateRequest {
    @NotNull
    private UUID bookId;
    private String description;
    @PositiveOrZero
    private Integer pageCount;
    @PositiveOrZero
    private Double rating;
    @PositiveOrZero
    private Integer bookmarked;
    @ValidEnum(enumClass = BookStatus.class, message = "{book.status.match}")
    private String bookStatus;
}
