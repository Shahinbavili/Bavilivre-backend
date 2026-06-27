package com.bavilivre.bavilivre_backend.application.usecase;

import com.bavilivre.bavilivre_backend.application.port.BookRepository;
import com.bavilivre.bavilivre_backend.application.port.BorrowingRepository;
import com.bavilivre.bavilivre_backend.domain.model.book.Book;
import com.bavilivre.bavilivre_backend.domain.model.book.BookId;
import com.bavilivre.bavilivre_backend.domain.model.borrowing.Borrowing;
import com.bavilivre.bavilivre_backend.domain.model.borrowing.BorrowingId;
import com.bavilivre.bavilivre_backend.domain.model.user.UserId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ReturnBookTest {

    private BorrowingRepository borrowingRepository;
    private BookRepository bookRepository;
    private ReturnBook returnBook;

    @BeforeEach
    void setUp() {
        borrowingRepository = mock(BorrowingRepository.class);
        bookRepository = mock(BookRepository.class);
        returnBook = new ReturnBook(borrowingRepository, bookRepository);
    }

    @Test
    void should_return_a_borrowed_book() {
        BorrowingId borrowingId = new BorrowingId(1);
        BookId bookId = new BookId(10);

        LocalDate borrowedAt = LocalDate.of(2026, 5, 19);
        LocalDate returnedAt = LocalDate.of(2026, 5, 24);

        Borrowing borrowing = new Borrowing(
                borrowingId,
                bookId,
                new UserId(2),
                new UserId(1),
                borrowedAt
        );

        Book book = new Book(
                bookId,
                new UserId(1),
                "Clean Code",
                "Robert C. Martin",
                "A book about clean code",
                "EN",
                "Programming",
                false
        );

        when(borrowingRepository.findById(borrowingId))
                .thenReturn(Optional.of(borrowing));

        when(bookRepository.findById(bookId))
                .thenReturn(Optional.of(book));

        when(borrowingRepository.save(any(Borrowing.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Borrowing returnedBorrowing = returnBook.handle(borrowingId, returnedAt);

        assertThat(returnedBorrowing.returnedAt()).isEqualTo(returnedAt);
        assertThat(returnedBorrowing.isReturned()).isTrue();

        verify(borrowingRepository).findById(borrowingId);
        verify(bookRepository).findById(bookId);
        verify(bookRepository).save(book.markAsAvailable());
        verify(borrowingRepository).save(any(Borrowing.class));
    }
}