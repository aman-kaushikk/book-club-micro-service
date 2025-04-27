package org.loop.troop.book.domain.request.tag;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class RemoveTagRequest {

	private List<String> tags = new ArrayList<>();

}