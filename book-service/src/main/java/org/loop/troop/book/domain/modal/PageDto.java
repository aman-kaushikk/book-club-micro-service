package org.loop.troop.book.domain.modal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * The type Page dto.
 *
 * @param <T> the type parameter
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
public class PageDto<T extends Serializable> implements Serializable {

	private List<T> content;

	private int page;

	private int size;

	private String sortBy;

	private long totalElement;

	private String sortDirection = "ASC";

}
