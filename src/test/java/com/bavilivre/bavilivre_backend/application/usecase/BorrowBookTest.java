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
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class BorrowBookTest {
    @Test
    void should_borrow_a_book_using_repository_ports() {

        BookRepository bookRepository = mock(BookRepository.class);
        UserRepository userRepository = mock(UserRepository.class);
        BorrowingRepository borrowingRepository = mock(BorrowingRepository.class);

        BorrowBook borrowBook = new BorrowBook(
                bookRepository,
                userRepository,
                borrowingRepository
        );

        BookId bookId = new BookId(7);
        UserId lenderId = new UserId(1);
        UserId borrowerId = new UserId(2);
        BorrowingId borrowingId = new BorrowingId(100);
        LocalDate borrowedAt = LocalDate.of(2026, 5, 19);

        Book book = new Book(
                bookId,
                lenderId,
                "Clean Architecture",
                "Robert C. Martin",
                "A guide to software architecture and clean boundaries.",
                "en",
                "Software Engineering");
        User borrower = new User(borrowerId);
        User lender = new User(lenderId);

        when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));
        when(userRepository.findById(borrowerId)).thenReturn(Optional.of(borrower));
        when(userRepository.findById(lenderId)).thenReturn(Optional.of(lender));
        when(borrowingRepository.save(any(Borrowing.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Borrowing borrowing = borrowBook.borrow(
                borrowingId,
                bookId,
                borrowerId,
                borrowedAt
        );

        assertThat(borrowing.id()).isEqualTo(borrowingId);
        assertThat(borrowing.bookId()).isEqualTo(bookId);
        assertThat(borrowing.borrowerId()).isEqualTo(borrowerId);
        assertThat(borrowing.lenderId()).isEqualTo(lenderId);
        assertThat(borrowing.borrowedAt()).isEqualTo(borrowedAt);
        verify(bookRepository).findById(bookId);
        verify(userRepository).findById(borrowerId);
        verify(userRepository).findById(lenderId);
        verify(borrowingRepository).save(any(Borrowing.class));
    }

}
