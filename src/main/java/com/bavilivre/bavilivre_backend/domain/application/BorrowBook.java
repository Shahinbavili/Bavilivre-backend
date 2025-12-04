package com.bavilivre.bavilivre_backend.domain.application;

import com.bavilivre.bavilivre_backend.domain.model.BookId;
import com.bavilivre.bavilivre_backend.domain.model.user.User;
import com.bavilivre.bavilivre_backend.domain.model.user.UserId;

public class BorrowBook {

    private final UserId lenderId;
    private final BookId bookId;
    private final UserId borrowerId;

    public BorrowBook(UserId lenderId, BookId bookId, UserId borrowerId) {
        this.lenderId = lenderId;
        this.bookId = bookId;
        this.borrowerId = borrowerId;
    }


    public BorrowedBooksDto fromAUserToAnotherUser() {
        User borrower = new User(borrowerId);
        User lender = new User(lenderId);

        borrower.borrow(bookId);
        lender.lend(bookId);

        return new BorrowDtoMapper().toDto(borrower);
    }


}
