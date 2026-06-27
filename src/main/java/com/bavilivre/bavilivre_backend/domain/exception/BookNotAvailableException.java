package com.bavilivre.bavilivre_backend.domain.exception;

import com.bavilivre.bavilivre_backend.domain.model.book.BookId;

public class BookNotAvailableException extends RuntimeException {
    public BookNotAvailableException(BookId bookId) {
        super("Book " + bookId.value() + " is not available");
    }
}
