package org.loop.troop.book;

import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.loop.troop.book.domain.AmazonBookScraperService;
import org.loop.troop.book.domain.BookScraperService;
import org.loop.troop.book.domain.modal.BookDto;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertNotNull;


public class BookScraperServiceTest {
    @Mock
    private Validator validator;

    private BookScraperService scraperService;
    @BeforeEach
    public void setUp() {
        // Initialize mocks
        MockitoAnnotations.openMocks(this);

        // Mock the actual book scraping service (AmazonBookScraperService)
        scraperService = new AmazonBookScraperService(validator);
    }
    @Test
    public void testRegister() {
        String testUrl = "https://www.amazon.in/Theft-winner-Nobel-Prize-Literature/dp/1526680106/ref=s9_acsd_al_bw_cv2_0_0_t?_encoding=UTF8&pf_rd_m=A21TJRUUN4KGV&pf_rd_s=merchandised-search-5&pf_rd_r=Q4A691JVDQN9SQER68B4&pf_rd_p=0300e79b-9e9b-449e-a39f-5a0ac27115b1&pf_rd_t=&pf_rd_i=976389031"; // use
        BookDto book = scraperService.validateAndRegister(testUrl);

        assertNotNull(book);
        assertNotNull(book.getTitle());
        assertNotNull(book.getAuthor());
        System.out.println("Title: " + book.getTitle());
        System.out.println("Author: " + book.getAuthor());
        System.out.println("Rating: " + book.getRating());
    }


}
