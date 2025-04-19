package org.loop.troop.book.domain.modal;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.loop.troop.book.domain.service.Vendor;
import org.loop.troop.book.web.validator.ValidEnum;

@Data
public class BookRequest {
    @NotBlank(message = "${book.url.missing}")
    private String url;
    @ValidEnum(enumClass = Vendor.class,message = "${invalid.vendor}")
    private String vendor;
}
