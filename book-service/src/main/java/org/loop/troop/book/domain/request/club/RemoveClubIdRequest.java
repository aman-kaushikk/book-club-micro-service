package org.loop.troop.book.domain.request.club;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class RemoveClubIdRequest {

	private List<UUID> clubIds = new ArrayList<>();

}