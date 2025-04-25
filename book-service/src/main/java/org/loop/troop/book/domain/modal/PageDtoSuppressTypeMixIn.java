package org.loop.troop.book.domain.modal;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * The type Page dto suppress type mix in.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
public abstract class PageDtoSuppressTypeMixIn {

}
