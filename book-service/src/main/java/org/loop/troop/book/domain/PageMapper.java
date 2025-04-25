package org.loop.troop.book.domain;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

import lombok.RequiredArgsConstructor;
import org.loop.troop.book.domain.modal.PageDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * The type Page mapper.
 *
 * @param <U> the type parameter
 * @param <T> the type parameter
 */
@Service
@RequiredArgsConstructor
public class PageMapper<U, T extends Serializable> {

	/**
	 * Convert to page.
	 * @param pageDto the page dto
	 * @param mapper the mapper
	 * @return the page
	 */
	public Page<U> convertToPage(PageDto<T> pageDto, Function<List<T>, List<U>> mapper) {
		Assert.notNull(pageDto, "PageDto object is mandatory");
		Sort sort = Objects.isNull(pageDto.getSortBy()) ? Sort.unsorted() : Sort.by(pageDto.getSortBy()).ascending();
		int DEFAULT_SIZE = 10;

		Pageable pageable = PageRequest.of(Math.max(pageDto.getPage(), 0),
				pageDto.getSize() > 0 ? pageDto.getSize() : DEFAULT_SIZE, sort);
		List<U> dtoList = mapper.apply(pageDto.getContent());
		return new PageImpl<>(dtoList, pageable, pageDto.getTotalElement());
	}

	/**
	 * Convert to dto page dto.
	 * @param page the page
	 * @param mapper the mapper
	 * @return the page dto
	 */
	public PageDto<T> convertToDto(Page<U> page, Function<List<U>, List<T>> mapper) {
		PageDto<T> pageDto = new PageDto<>();
		Assert.notNull(page, "PageDto object is mandatory");
		long totalElements = page.getTotalElements();
		long size = page.getSize();
		int pageNumber = page.getNumber();
		Sort sort = page.getSort();
		if (sort.isSorted()) {
			Sort.Order order = sort.iterator().next();
			pageDto.setSortBy(order.getProperty()); // e.g., "title"
			pageDto.setSortDirection(order.getDirection().name()); // "ASC" or "DESC"
		}
		pageDto.setTotalElement(totalElements);
		pageDto.setSize((int) size);
		pageDto.setPage(pageNumber);
		pageDto.setContent(mapper.apply(page.getContent()));
		return pageDto;
	}

}