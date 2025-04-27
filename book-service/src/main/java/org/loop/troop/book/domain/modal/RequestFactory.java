package org.loop.troop.book.domain.modal;

import lombok.extern.slf4j.Slf4j;
import org.loop.troop.book.domain.request.buylinks.AddBuyLinkRequest;
import org.loop.troop.book.domain.request.buylinks.RemoveBuyLinkRequest;
import org.loop.troop.book.domain.request.club.AddClubIdRequest;
import org.loop.troop.book.domain.request.club.RemoveClubIdRequest;
import org.loop.troop.book.domain.request.genre.AddGenreRequest;
import org.loop.troop.book.domain.request.genre.RemoveGenreRequest;
import org.loop.troop.book.domain.request.tag.AddTagRequest;
import org.loop.troop.book.domain.request.tag.RemoveTagRequest;

import java.util.List;
import java.util.UUID;

@Slf4j
public class RequestFactory {

	public static AddBuyLinkRequest createAddBuyLinkRequest(List<BuyLinkDto> buyLinks) {
		AddBuyLinkRequest request = new AddBuyLinkRequest();
		request.setBuyLinks(buyLinks);
		log.info("Add Buy Link Request created with buyLinks: {}", buyLinks);
		return request;
	}

	public static RemoveBuyLinkRequest createRemoveBuyLinkRequest(List<BuyLinkDto> buyLinks) {
		RemoveBuyLinkRequest request = new RemoveBuyLinkRequest();
		request.setBuyLinks(buyLinks);
		log.info("Remove Buy Link Request created with buyLinks: {}", buyLinks);
		return request;
	}

	public static AddClubIdRequest createAddClubIdRequest(List<UUID> clubIds) {
		AddClubIdRequest request = new AddClubIdRequest();
		request.setClubIds(clubIds);
		log.info("Add Club ID Request created with clubIds: {}", clubIds);
		return request;
	}

	public static RemoveClubIdRequest createRemoveClubIdRequest(List<UUID> clubIds) {
		RemoveClubIdRequest request = new RemoveClubIdRequest();
		request.setClubIds(clubIds);
		log.info("Remove Club ID Request created with clubIds: {}", clubIds);
		return request;
	}

	public static AddGenreRequest createAddGenreRequest(List<String> genres) {
		AddGenreRequest request = new AddGenreRequest();
		request.setGenres(genres);
		log.info("Add Genre Request created with genres: {}", genres);
		return request;
	}

	public static RemoveGenreRequest createRemoveGenreRequest(List<String> genres) {
		RemoveGenreRequest request = new RemoveGenreRequest();
		request.setGenres(genres);
		log.info("Remove Genre Request created with genres: {}", genres);
		return request;
	}

	public static AddTagRequest createAddTagRequest(List<String> tags) {
		AddTagRequest request = new AddTagRequest();
		request.setTags(tags);
		log.info("Add Tag Request created with tags: {}", tags);
		return request;
	}

	public static RemoveTagRequest createRemoveTagRequest(List<String> tags) {
		RemoveTagRequest request = new RemoveTagRequest();
		request.setTags(tags);
		log.info("Remove Tag Request created with tags: {}", tags);
		return request;
	}

}
