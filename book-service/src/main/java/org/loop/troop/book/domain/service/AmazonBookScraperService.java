package org.loop.troop.book.domain.service;

import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.loop.troop.book.domain.BookStatus;
import org.loop.troop.book.domain.ServiceException;
import org.loop.troop.book.domain.UrlScrapingException;
import org.loop.troop.book.domain.Utility;
import org.loop.troop.book.domain.modal.BaseDto;
import org.loop.troop.book.domain.modal.BookDto;
import org.loop.troop.book.domain.modal.BuyLinkDto;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>
 * AmazonBookScraperService class.
 * </p>
 *
 * @author alex
 */
@Service
@Slf4j
class AmazonBookScraperService extends BookScraperService {

	/**
	 * The constant N_A.
	 */
	public static final String N_A = "N/A";

	/**
	 * Instantiates a new Amazon book scraper service.
	 * @param validator the validator
	 * @param vendor the vendor
	 */
	public AmazonBookScraperService(Validator validator, @Qualifier("amazonVendor") Vendor vendor) {
		super(validator, vendor);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public BookDto register(String url) {
		try {
			Map<String, String> extractBookInfo = extractBookInfo(url);
			validateScrapingUrl(extractBookInfo);
			return getBookDto(extractBookInfo, url);
		}
		catch (UrlScrapingException e) {
			throw new UrlScrapingException(e.getMessage());
		}
		catch (Exception e) {
			Utility.printStackTrace(e);
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public boolean support(Vendor vendor) {
		return super.vendor.name().equals(vendor.name());
	}

	/**
	 * <p>
	 * extractBookInfo.
	 * </p>
	 * @param amazonUrl a {@link java.lang.String} object
	 * @return a {@link java.util.Map} object
	 * @throws IOException the io exception
	 */
	public Map<String, String> extractBookInfo(String amazonUrl) throws IOException {
		Document doc = Jsoup.connect(amazonUrl).userAgent("Mozilla/5.0").timeout(10_000).get();

		Map<String, String> bookInfo = new HashMap<>();
		// Extract title
		addElement(doc, "#productTitle", bookInfo, "title");
		// Extract sub- title
		addElement(doc, "#productSubtitle", bookInfo, "subTitle");
		// Extract author
		addElement(doc, "#bylineInfo .author a", bookInfo, "author");
		// Extract description
		Element descriptionWrapper = doc.selectFirst("#bookDescription_feature_div .a-expander-content");
		if (descriptionWrapper != null) {
			String html = descriptionWrapper.html();
			String descriptionMarkdown = markDownConverterFromHtml(html);
			bookInfo.put("description", descriptionMarkdown);
		}
		// Extract image
		Element image = doc.selectFirst("#imgTagWrapperId img");
		String imageUrl = image != null ? image.attr("src") : N_A;
		bookInfo.put("bookUrl", imageUrl);
		// Extract page count
		Element pageCountElement = doc.selectFirst("#rpi-attribute-book_details-fiona_pages .rpi-attribute-value span");
		if (pageCountElement != null) {
			String text = pageCountElement.text(); // "454 pages"
			// Use regex to extract digits
			Pattern pattern = Pattern.compile("\\d+");
			Matcher matcher = pattern.matcher(text);

			if (matcher.find()) {
				String pages = matcher.group(); // "454"
				bookInfo.put("pageCount", pages);
			}
		}
		return bookInfo;
	}

	private static void addElement(Document doc, String hashtag, Map<String, String> bookInfo, String title) {
		Element element = doc.selectFirst(hashtag);
		String result = element != null ? element.text() : N_A;
		bookInfo.put(title, result);
	}

	private BookDto getBookDto(Map<String, String> extractBookInfo, String url) {
		BookDto bookDto = new BookDto();
		bookDto.setDescription(extractBookInfo.get("description"));
		bookDto.setAuthor(extractBookInfo.get("author"));
		bookDto.setTitle(extractBookInfo.get("title"));
		bookDto.setUrl(extractBookInfo.get("bookUrl"));
		BaseDto baseDto = getBaseDto(UUID.randomUUID(), UUID.randomUUID());
		bookDto.setCreatedAt(baseDto.getCreatedAt());
		bookDto.setCreatedBy(baseDto.getCreatedBy());
		bookDto.setUpdatedAt(baseDto.getUpdatedAt());
		bookDto.setPageCount(Integer.valueOf(extractBookInfo.get("pageCount")));
		BuyLinkDto buyLinkDto = new BuyLinkDto();
		try {
			url = Utility.sanitizeUrl(url);
		}
		catch (URISyntaxException e) {
			throw new ServiceException("Cannot Sanitize Url : " + e.getMessage());
		}
		buyLinkDto.setUrl(url);
		buyLinkDto.setVendor(Vendor.AMAZON.name());
		bookDto.setBuyLinks(Collections.singletonList(buyLinkDto));
		bookDto.setRating(0d);
		bookDto.setTags(List.of("amazon-book"));
		bookDto.setBookStatus(BookStatus.UNKNOWN.name());
		return bookDto;
	}

	private BaseDto getBaseDto(UUID createdBy, UUID updatedBy) {
		BaseDto baseDto = new BaseDto();
		baseDto.setCreatedAt(LocalDateTime.now());
		baseDto.setCreatedBy(createdBy);
		baseDto.setUpdatedBy(updatedBy);
		return baseDto;
	}

	private void validateScrapingUrl(Map<String, String> contextMap) {
		List<String> requiredFields = List.of("title", "author", "description", "bookUrl");

		for (String field : requiredFields) {
			String value = contextMap.get(field);
			if (value == null || value.trim().isEmpty() || value.equalsIgnoreCase("N/A")) {
				throw new UrlScrapingException("URL validation failed for field: " + value);
			}
		}
	}

	private String markDownConverterFromHtml(String html) {
		if (html == null) {
			return N_A;
		}
		return html.replaceAll("(?i)<br\\s*/?>", "\n")
			.replaceAll("(?i)<span[^>]*>", "")
			.replaceAll("(?i)</span>", "")
			.replaceAll("(?i)<p[^>]*>", "")
			.replaceAll("(?i)</p>", "\n\n")
			.replaceAll("(?i)<strong>|<b>|<span class=\"a-text-bold\">", "**")
			.replaceAll("(?i)</strong>|</b>|</span>", "**")
			.replaceAll("(?i)<em>|<i>|<span class=\"a-text-italic\">", "*")
			.replaceAll("(?i)</em>|</i>|</span>", "*")
			.replaceAll("(?i)<[^>]+>", "")
			.replaceAll("&nbsp;", " ")
			.replaceAll("&amp;", "&")
			.trim();
	}

}
