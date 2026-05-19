package com.bavilivre.bavilivre_backend.application.usecase;

import com.bavilivre.bavilivre_backend.application.port.BookRepository;
import com.bavilivre.bavilivre_backend.application.port.BorrowingRepository;
import com.bavilivre.bavilivre_backend.application.port.UserRepository;
import com.bavilivre.bavilivre_backend.domain.model.book.Book;
import com.bavilivre.bavilivre_backend.domain.model.book.BookId;
import com.bavilivre.bavilivre_backend.domain.model.borrowing.Borrowing;
import com.bavilivre.bavilivre_backend.domain.model.borrowing.BorrowingId;
import com.bavilivre.bavilivre_backend.domain.model.user.User;
import com.bavilivre.bavilivre_backend.domain.model.user.UserId;

import java.time.LocalDate;

public class BorrowBook {

    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final BorrowingRepository borrowingRepository;

    public BorrowBook(
            BookRepository bookRepository,
            UserRepository userRepository,
            BorrowingRepository borrowingRepository
    ) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
        this.borrowingRepository = borrowingRepository;
    }

    public Borrowing borrow(
            BorrowingId borrowingId,
            BookId bookId,
            UserId borrowerId,
            LocalDate borrowedAt
    ) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("Book not found"));

        User borrower = userRepository.findById(borrowerId)
                .orElseThrow(() -> new IllegalArgumentException("Borrower not found"));

        User lender = userRepository.findById(book.ownerId())
                .orElseThrow(() -> new IllegalArgumentException("Lender not found"));
        Borrowing borrowing = new Borrowing(
                borrowingId,
                book.id(),
                borrower.id(),
                lender.id(),
                borrowedAt
        );
        return borrowingRepository.save(borrowing);
    }
}
