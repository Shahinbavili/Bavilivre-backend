package com.bavilivre.bavilivre_backend.domain.exception;

import com.bavilivre.bavilivre_backend.domain.model.borrowing.BorrowingId;

public class BookAlreadyReturnedException extends RuntimeException {
    public BookAlreadyReturnedException(BorrowingId id) {
        super("Borrowing " + id.value() + " is already returned");
    }
}