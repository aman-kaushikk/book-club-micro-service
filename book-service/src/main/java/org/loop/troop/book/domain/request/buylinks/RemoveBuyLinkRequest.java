package org.loop.troop.book.domain.request.buylinks;

import lombok.Data;
import org.loop.troop.book.domain.modal.BuyLinkDto;

import java.util.ArrayList;
import java.util.List;

@Data
public class RemoveBuyLinkRequest {

	private List<BuyLinkDto> buyLinks = new ArrayList<>();

}