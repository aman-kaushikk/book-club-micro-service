package org.loop.troop.book.domain.request.buylinks;

import jakarta.validation.Valid;
import lombok.Data;
import org.loop.troop.book.domain.modal.BuyLinkDto;

import java.util.ArrayList;
import java.util.List;

@Data
public class AddBuyLinkRequest {

	@Valid
	private List<BuyLinkDto> buyLinks = new ArrayList<>();

}
