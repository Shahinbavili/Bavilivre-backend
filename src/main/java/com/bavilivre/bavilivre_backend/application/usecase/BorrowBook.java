package com.bavilivre.bavilivre_backend.application.usecase;

import com.bavilivre.bavilivre_backend.application.dto.BorrowedBooksDto;
import com.bavilivre.bavilivre_backend.application.mapper.BorrowDtoMapper;
import com.bavilivre.bavilivre_backend.domain.model.book.Book;
import com.bavilivre.bavilivre_backend.domain.model.user.User;
import com.bavilivre.bavilivre_backend.domain.model.user.UserId;

public class BorrowBook {

    private final UserId lenderId;
    private final Book book;
    private final UserId borrowerId;

    public BorrowBook(UserId lenderId, Book book, UserId borrowerId) {
        this.lenderId = lenderId;
        this.book = book;
        this.borrowerId = borrowerId;
    }


    public BorrowedBooksDto fromAUserToAnotherUser() {
        User borrower = new User(borrowerId);
        User lender = new User(lenderId);

        borrower.borrow(book);
        lender.lend(book);

        return new BorrowDtoMapper().toDto(borrower);
    }


}
