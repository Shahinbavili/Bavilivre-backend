package com.bavilivre.bavilivre_backend.domain.exception;

import com.bavilivre.bavilivre_backend.domain.model.borrowing.BorrowingId;

public class BorrowingNotFoundException extends RuntimeException {

    public BorrowingNotFoundException(BorrowingId id) {
        super("Borrowing " + id.value() + " not found");
    }
}