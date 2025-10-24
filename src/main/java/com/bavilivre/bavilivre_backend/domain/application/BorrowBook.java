package com.bavilivre.bavilivre_backend.domain.application;

import com.bavilivre.bavilivre_backend.domain.model.BookId;
import com.bavilivre.bavilivre_backend.domain.model.user.User;
import com.bavilivre.bavilivre_backend.domain.model.user.UserId;

import java.util.HashSet;
import java.util.Set;

public class BorrowBook {

    private UserId lenderId;
    private BookId bookId;
    private UserId borrowerId;

    public BorrowBook(UserId lenderId, BookId bookId, UserId borrowerId) {
        this.lenderId = lenderId;
        this.bookId = bookId;
        this.borrowerId = borrowerId;
    }


    public BorrowDto fromAUserToAnotherUser() {
        User borrower = new User(borrowerId);
        User lender = new User(lenderId);

        borrower.borrow(bookId);
        BorrowDto borrowDto = new BorrowDto();
        final Set<BookId> bookIds = borrower.borrowedBooks();

        Set<Integer> borrowedBookIds = new HashSet<>();
        for (BookId bookId : bookIds) {
            borrowedBookIds.add(bookId.getId());
        }
        borrowDto.setBorrowedBookList(borrowedBookIds);

        return borrowDto;
    }
}
