package org.loop.troop.book.domain.modal;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class RowEffected {

	Map<String, Object> rows = new HashMap<>();

	int rowCount = 0;

}
