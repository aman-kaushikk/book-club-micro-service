-- Foreign key: book_buy_link → books
ALTER TABLE IF EXISTS book_buy_link
ADD CONSTRAINT fk_book_buy_links_book
FOREIGN KEY (book_id_fk)
REFERENCES book(book_id);

-- Foreign key: book_club → books
ALTER TABLE IF EXISTS book_club
ADD CONSTRAINT fk_book_clubs_book
FOREIGN KEY (book_id_fk)
REFERENCES book(book_id);

-- Foreign key: book_genre → book
ALTER TABLE IF EXISTS book_genre
ADD CONSTRAINT fk_book_genre
FOREIGN KEY (book_id_fk)
REFERENCES book(book_id);

-- Foreign key: book_tag → book
ALTER TABLE IF EXISTS book_tag
ADD CONSTRAINT fk_book_tag
FOREIGN KEY (book_id_fk)
REFERENCES book(book_id);

-- Foreign key: reviews → books
ALTER TABLE IF EXISTS review
ADD CONSTRAINT fk_reviews_book
FOREIGN KEY (book_id_fk)
REFERENCES book(book_id);
