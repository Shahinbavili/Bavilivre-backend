package com.bavilivre.bavilivre_backend.domain.application;

import com.bavilivre.bavilivre_backend.application.BookId;
import com.bavilivre.bavilivre_backend.application.UserId;

public class BorrowBook {

    private UserId lender;
    private BookId bookId;
    private UserId borrower;

    public BorrowBook(UserId lender, BookId bookId, UserId borrower) {
        this.lender = lender;
        this.bookId = bookId;
        this.borrower = borrower;
    }


    public void fromAUserToAnotherUser() {

    }
}
