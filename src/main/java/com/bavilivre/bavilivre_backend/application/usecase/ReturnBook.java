package com.bavilivre.bavilivre_backend.application.usecase;

import com.bavilivre.bavilivre_backend.application.port.BookRepository;
import com.bavilivre.bavilivre_backend.application.port.BorrowingRepository;
import com.bavilivre.bavilivre_backend.domain.exception.BookNotFoundException;
import com.bavilivre.bavilivre_backend.domain.exception.BorrowingNotFoundException;
import com.bavilivre.bavilivre_backend.domain.model.book.Book;
import com.bavilivre.bavilivre_backend.domain.model.borrowing.Borrowing;
import com.bavilivre.bavilivre_backend.domain.model.borrowing.BorrowingId;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ReturnBook {

    private final BorrowingRepository borrowingRepository;
    private final BookRepository bookRepository;

    public ReturnBook(BorrowingRepository borrowingRepository, BookRepository bookRepository) {
        this.borrowingRepository = borrowingRepository;
        this.bookRepository = bookRepository;
    }

    public Borrowing handle(
            BorrowingId borrowingId,
            LocalDate returnedAt
    ) {
        Borrowing borrowing = borrowingRepository.findById(borrowingId)
                .orElseThrow(() -> new BorrowingNotFoundException(borrowingId));

        borrowing.returnBook(returnedAt);

        Book book = bookRepository.findById(borrowing.bookId())
                .orElseThrow(() -> new BookNotFoundException(borrowing.bookId()));

        Book availableBook = book.markAsAvailable();
        bookRepository.save(availableBook);

        return borrowingRepository.save(borrowing);
    }
}