package com.bavilivre.bavilivre_backend.application.usecase;

import com.bavilivre.bavilivre_backend.infrastructure.controller.response.BorrowedBooksDto;
import com.bavilivre.bavilivre_backend.application.mapper.BorrowDtoMapper;
import com.bavilivre.bavilivre_backend.domain.model.book.Book;
import com.bavilivre.bavilivre_backend.domain.model.book.BookId;
import com.bavilivre.bavilivre_backend.domain.model.user.User;
import com.bavilivre.bavilivre_backend.domain.model.user.UserId;
import org.springframework.stereotype.Service;

@Service
public class GetBorrowedBooks {

    private final BorrowDtoMapper borrowDtoMapper;

    public GetBorrowedBooks(BorrowDtoMapper borrowDtoMapper) {
        this.borrowDtoMapper = borrowDtoMapper;
    }

    public BorrowedBooksDto handle(Integer userId) {
        // temporary simulation
        User borrower = new User(new UserId(userId));

        borrower.borrow(new Book(new BookId(1), new UserId(10)));
        borrower.borrow(new Book(new BookId(2), new UserId(11)));

        return borrowDtoMapper.toDto(borrower);
    }
}