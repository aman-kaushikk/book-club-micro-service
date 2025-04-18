package org.loop.troop.book.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class BuyLink {

    @Column(name = "vendor", nullable = false)
    private String vendor;

    @Column(name = "url", nullable = false)
    private String url;
}

