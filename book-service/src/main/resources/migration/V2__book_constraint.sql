-- Foreign key: book_buy_links → books
ALTER TABLE IF EXISTS book_buy_links
ADD CONSTRAINT fk_book_buy_links_book
FOREIGN KEY (book_id)
REFERENCES books(book_id);

-- Foreign key: book_clubs → books
ALTER TABLE IF EXISTS book_clubs
ADD CONSTRAINT fk_book_clubs_book
FOREIGN KEY (book_id)
REFERENCES books(book_id);

-- Foreign key: reviews → books
ALTER TABLE IF EXISTS reviews
ADD CONSTRAINT fk_reviews_book
FOREIGN KEY (book_id)
REFERENCES books(book_id);
