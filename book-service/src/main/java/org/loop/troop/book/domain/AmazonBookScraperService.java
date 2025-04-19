package org.loop.troop.book.domain;

import jakarta.validation.Validator;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.loop.troop.book.domain.modal.BaseDto;
import org.loop.troop.book.domain.modal.BookDto;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * <p>
 * AmazonBookScraperService class.
 * </p>
 *
 * @author alex
 */
@Service
public class AmazonBookScraperService extends BookScraperService {

	public static final String N_A = "N/A";

    public AmazonBookScraperService(Validator validator) {
		super(validator);
    }

	/** {@inheritDoc} */
	@Override
	public BookDto register(String url) {
		try {
			Map<String, String> extractBookInfo = extractBookInfo(url);
			validateScrapingUrl(extractBookInfo);
			BookDto bookDto = new BookDto();
			bookDto.setBookId(UUID.randomUUID());
			bookDto.setDescription(extractBookInfo.get("description"));
			bookDto.setAuthor(extractBookInfo.get("author"));
			bookDto.setTitle(extractBookInfo.get("title"));
			bookDto.setUrl(extractBookInfo.get("bookUrl"));
			BaseDto baseDto = getBaseDto(UUID.randomUUID(), UUID.randomUUID());
			bookDto.setCreatedAt(baseDto.getCreatedAt());
			bookDto.setCreatedBy(baseDto.getCreatedBy());
			bookDto.setUpdatedAt(baseDto.getUpdatedAt());
			bookDto.setRating(0d);

			return bookDto;
		}
		catch (IOException e) {
			throw new UrlScrapingException(e.getMessage());
		}
    }

	/**
	 * <p>
	 * extractBookInfo.
	 * </p>
	 * @param amazonUrl a {@link java.lang.String} object
	 * @return a {@link java.util.Map} object
	 * @throws java.io.IOException if any.
	 */
	public Map<String, String> extractBookInfo(String amazonUrl) throws IOException {
		Document doc = Jsoup.connect(amazonUrl).userAgent("Mozilla/5.0").timeout(10_000).get();

		Map<String, String> bookInfo = new HashMap<>();

		// Extract title
		Element titleElement = doc.selectFirst("#productTitle");
		String title = titleElement != null ? titleElement.text() : N_A;
		bookInfo.put("title",title);
		// Extract sub- title
		Element subTitleElement = doc.selectFirst("#productSubtitle");
		String subTitle = subTitleElement!=null ? subTitleElement.text() : N_A;
		bookInfo.put("subTitle", subTitle);
		// Extract author
		Element author = doc.selectFirst("#bylineInfo .author a");
		String authorName = author != null ? author.text() : N_A;
		bookInfo.put("author", authorName);
		// Extract description
		Element descriptionWrapper = doc.selectFirst("#bookDescription_feature_div .a-expander-content");
		if (descriptionWrapper != null) {
			String html = descriptionWrapper.html();
			String descriptionMarkdown = markDownConverterFromHtml(html);
			bookInfo.put("description",descriptionMarkdown);
		}
		// Extract image
		Element image = doc.selectFirst("#imgTagWrapperId img");
		String imageUrl = image != null ? image.attr("src") : N_A;
		bookInfo.put("bookUrl",imageUrl);

		return bookInfo;
	}


	private void validateScrapingUrl(Map<String, String> contextMap){
		List<String> requiredFields = List.of("title", "author", "description", "bookUrl");

		for (String field : requiredFields) {
			String value = contextMap.get(field);
			if (value == null || value.trim().isEmpty() || value.equalsIgnoreCase("N/A")) {
				throw new UrlScrapingException("URL validation failed for field: " + value);
			}
		}
	}

	private BaseDto getBaseDto(UUID createdBy, UUID updatedBy){
		BaseDto baseDto = new BaseDto();
		baseDto.setCreatedAt(LocalDateTime.now());
		baseDto.setCreatedBy(createdBy);
		baseDto.setUpdatedBy(updatedBy);
		return baseDto;
	}

	private String markDownConverterFromHtml(String html){
		if(html == null){
			return N_A;
		}
		return html
				.replaceAll("(?i)<br\\s*/?>", "\n")
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
