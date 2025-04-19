package org.loop.troop.book;

import org.loop.troop.book.domain.BookService;
import org.loop.troop.book.domain.service.Vendor;
import org.loop.troop.book.domain.modal.BookDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * The type Book service application.
 *
 * @author alex
 */
@SpringBootApplication
@EnableTransactionManagement
public class BookServiceApplication implements CommandLineRunner {

	private static final Logger log = LoggerFactory.getLogger(BookServiceApplication.class);

	/**
	 * The entry point of application.
	 * @param args the input arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(BookServiceApplication.class, args);
	}

	@Autowired
	private BookService bookService;

	@Override
	public void run(String... args) throws Exception {
		String testUrl = "https://www.amazon.in/dp/B09C745BCM/ref=sspa_dk_detail_1?psc=1&pd_rd_i=B09C745BCM&pd_rd_w=MaT36&content-id=amzn1.sym.413ef885-ae1b-472f-afa4-d683cda6ad0d&pf_rd_p=413ef885-ae1b-472f-afa4-d683cda6ad0d&pf_rd_r=9T8YG88G5CPY81YW9VHS&pd_rd_wg=IwgNU&pd_rd_r=81f914f1-3be9-4c9c-a5a9-c0a3edfec756&s=books&sp_csd=d2lkZ2V0TmFtZT1zcF9kZXRhaWw"; // use

		BookDto book = bookService.register(testUrl, Vendor.AMAZON);
		log.info(String.valueOf(book));
	}

}
