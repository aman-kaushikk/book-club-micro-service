package org.loop.troop.book.domain;

class UrlScrapingException extends RuntimeException {
    
    public UrlScrapingException(String message) {
        super(message);
    }

    public UrlScrapingException(String message, Throwable cause) {
        super(message, cause);
    }
}