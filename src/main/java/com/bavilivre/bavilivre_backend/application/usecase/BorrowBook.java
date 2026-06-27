package com.bavilivre.bavilivre_backend.application.usecase;

import com.bavilivre.bavilivre_backend.application.port.BookRepository;
import com.bavilivre.bavilivre_backend.application.port.BorrowingRepository;
import com.bavilivre.bavilivre_backend.application.port.UserRepository;
import com.bavilivre.bavilivre_backend.domain.exception.BookNotAvailableException;
import com.bavilivre.bavilivre_backend.domain.exception.BookNotFoundException;
import com.bavilivre.bavilivre_backend.domain.exception.UserNotFoundException;
import com.bavilivre.bavilivre_backend.domain.model.book.Book;
import com.bavilivre.bavilivre_backend.domain.model.book.BookId;
import com.bavilivre.bavilivre_backend.domain.model.borrowing.Borrowing;
import com.bavilivre.bavilivre_backend.domain.model.borrowing.BorrowingId;
import com.bavilivre.bavilivre_backend.domain.model.user.User;
import com.bavilivre.bavilivre_backend.domain.model.user.UserId;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
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
                .orElseThrow(() -> new BookNotFoundException(bookId));

        if (!book.available()) {
            throw new BookNotAvailableException(bookId);
        }

        User borrower = userRepository.findById(borrowerId)
                .orElseThrow(() -> new UserNotFoundException(borrowerId));

        User lender = userRepository.findById(book.ownerId())
                .orElseThrow(() -> new UserNotFoundException(book.ownerId()));
        Borrowing borrowing = new Borrowing(
                borrowingId,
                book.id(),
                borrower.id(),
                lender.id(),
                borrowedAt
        );

        Book borrowedBook = book.markAsBorrowed();
        bookRepository.save(borrowedBook);

        return borrowingRepository.save(borrowing);
    }
}
