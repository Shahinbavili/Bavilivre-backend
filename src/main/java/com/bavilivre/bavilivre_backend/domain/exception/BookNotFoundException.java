package com.bavilivre.bavilivre_backend.domain.exception;

import com.bavilivre.bavilivre_backend.domain.model.book.BookId;

public class BookNotFoundException extends RuntimeException {

    public BookNotFoundException(BookId id) {
        super("Book " + id.value() + " not found");
    }
}