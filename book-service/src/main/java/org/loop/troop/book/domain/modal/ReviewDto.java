package org.loop.troop.book.domain.modal;

import lombok.*;

import java.util.UUID;

/**
 * The type Review dto.
 *
 * @author alex
 */
@Data
public class ReviewDto {

	private UUID id;

	private UUID userId;

	private String userProfileUrl;

	private String reviewDescription;

	private boolean inappropriate;

	private Double star;

}
