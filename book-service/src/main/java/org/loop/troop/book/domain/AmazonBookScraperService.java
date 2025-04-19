package org.loop.troop.book.domain;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.loop.troop.book.domain.modal.BookDto;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>AmazonBookScraperService class.</p>
 *
 * @author alex
 */
@Service
public class AmazonBookScraperService implements BookScraperService {

	/** {@inheritDoc} */
	@Override
	public BookDto register(String url) {
		try {
			Map<String, String> extractBookInfo = extractBookInfo(url);
		}
		catch (IOException e) {
			throw new RuntimeException(e);
		}
		return null;
	}

	/**
	 * <p>extractBookInfo.</p>
	 *
	 * @param amazonUrl a {@link java.lang.String} object
	 * @return a {@link java.util.Map} object
	 * @throws java.io.IOException if any.
	 */
	public Map<String, String> extractBookInfo(String amazonUrl) throws IOException {
		Document doc = Jsoup.connect(amazonUrl).userAgent("Mozilla/5.0").timeout(10_000).get();

		Map<String, String> bookInfo = new HashMap<>();

		// Extract title
		Element title = doc.selectFirst("#productTitle");
		bookInfo.put("title", title != null ? title.text() : "N/A");

		// Extract author
		Element author = doc.selectFirst(".author a.a-link-normal");
		bookInfo.put("author", author != null ? author.text() : "N/A");

		// Extract rating
		Element rating = doc.selectFirst("span[data-asin] i span.a-icon-alt");
		bookInfo.put("rating", rating != null ? rating.text() : "N/A");

		// Extract description
		Element description = doc.selectFirst("#bookDescription_feature_div noscript");
		bookInfo.put("description", description != null ? description.text() : "N/A");

		// Extract image
		Element image = doc.selectFirst("#imgBlkFront, #ebooksImgBlkFront");
		bookInfo.put("image", image != null ? image.attr("src") : "N/A");

		return bookInfo;
	}

}
