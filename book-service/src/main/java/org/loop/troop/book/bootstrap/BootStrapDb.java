package org.loop.troop.book.bootstrap;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.loop.troop.book.domain.BookService;
import org.loop.troop.book.domain.ServiceException;
import org.loop.troop.book.domain.Utility;
import org.loop.troop.book.domain.request.book.BookRequest;
import org.loop.troop.book.domain.modal.BuyLinkDto;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Boot strap db.
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class BootStrapDb implements ApplicationRunner {

	private final BookService bookService;

	private final ResourceLoader resourceLoader;

	private final RegisterBookThread bookThread;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		log.info(
				"----------------------------------------- Bootstrapping Books --------------------------------------------");
		List<BookRequest> bookRequestList = bookRequestBuild();
		List<String> buyLinkList = bookService.getAllBuyLink().stream().map(BuyLinkDto::getUrl).toList();
		bookRequestList = bookRequestList.stream().filter(bl -> !buyLinkList.contains(bl.getUrl())).toList();
		try {
			log.info("BootStrapping DB server with books");
			for (BookRequest request : bookRequestList) {
				bookThread.registerBook(request);
			}
		}
		catch (Exception e) {
			log.error("Exception occurred during bootstrap: {}", e.getMessage());
		}

	}

	private List<BookRequest> bookRequestBuild() throws IOException {
		Resource resource = resourceLoader.getResource("classpath:data/books.csv");
		List<BookRequest> bookRequests = new ArrayList<>();
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()))) {
			String line;
			boolean firstLine = true;

			while ((line = reader.readLine()) != null) {
				if (firstLine) {
					firstLine = false;
					continue;
				}

				String[] tokens = line.split(",");
				if (tokens.length == 2) {
					BookRequest bookRequest = new BookRequest();
					bookRequest.setVendor(tokens[0].trim());
					String url = tokens[1].trim();
					try {
						url = Utility.sanitizeUrl(url);
						log.info("SanitizeUrl : {}", url);
					}
					catch (URISyntaxException e) {
						throw new ServiceException("Cannot Sanitize Url : " + e.getMessage());
					}
					bookRequest.setUrl(url);
					bookRequests.add(bookRequest);
				}
			}
		}
		return bookRequests;
	}

}