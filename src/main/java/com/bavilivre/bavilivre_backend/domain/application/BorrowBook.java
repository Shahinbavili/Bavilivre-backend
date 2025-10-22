package com.bavilivre.bavilivre_backend.domain.application;

import com.bavilivre.bavilivre_backend.domain.model.BookId;
import com.bavilivre.bavilivre_backend.domain.model.user.UserId;

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
