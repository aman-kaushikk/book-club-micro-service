package org.loop.troop.book.domain.request.genre;

import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
public class RemoveGenreRequest {

	private List<String> genres = new ArrayList<>();

}