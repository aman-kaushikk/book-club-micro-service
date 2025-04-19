package org.loop.troop.book;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class BookServiceApplication {

	private static final Logger log = LoggerFactory.getLogger(BookServiceApplication.class);

	/**
	 * The entry point of application.
	 * @param args the input arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(BookServiceApplication.class, args);
	}

}
