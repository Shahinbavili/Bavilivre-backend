package com.bavilivre.bavilivre_backend.domain.model.borrowing;

import com.bavilivre.bavilivre_backend.domain.model.book.BookId;
import com.bavilivre.bavilivre_backend.domain.model.user.UserId;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BorrowingTest {

    @Test
    void should_create_active_borrowing() {

        Borrowing borrowing = new Borrowing(
                new BorrowingId(1),
                new BookId(10),
                new UserId(2),
                new UserId(3),
                LocalDate.of(2026, 4, 30)
        );

        assertThat(borrowing.id().value()).isEqualTo(1);
        assertThat(borrowing.bookId().value()).isEqualTo(10);
        assertThat(borrowing.borrowerId().value()).isEqualTo(2);
        assertThat(borrowing.lenderId().value()).isEqualTo(3);

        assertThat(borrowing.borrowedAt())
                .isEqualTo(LocalDate.of(2026, 4, 30));

        assertThat(borrowing.returnedAt()).isNull();
        assertThat(borrowing.isReturned()).isFalse();
    }

    @Test
    void should_mark_borrowing_as_returned() {

        Borrowing borrowing = new Borrowing(
                new BorrowingId(1),
                new BookId(10),
                new UserId(2),
                new UserId(3),
                LocalDate.of(2026, 4, 30)
        );

        borrowing.returnBook(LocalDate.of(2026, 5, 5));

        assertThat(borrowing.returnedAt())
                .isEqualTo(LocalDate.of(2026, 5, 5));

        assertThat(borrowing.isReturned()).isTrue();
    }

    @Test
    void should_not_return_book_twice() {

        Borrowing borrowing = new Borrowing(
                new BorrowingId(1),
                new BookId(10),
                new UserId(2),
                new UserId(3),
                LocalDate.of(2026, 4, 30)
        );

        borrowing.returnBook(LocalDate.of(2026, 5, 5));

        assertThatThrownBy(() ->
                borrowing.returnBook(LocalDate.of(2026, 5, 6))
        )
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("Book already returned");
    }

    @Test
    void should_not_return_before_borrow_date() {

        Borrowing borrowing = new Borrowing(
                new BorrowingId(1),
                new BookId(10),
                new UserId(2),
                new UserId(3),
                LocalDate.of(2026, 4, 30)
        );

        assertThatThrownBy(() ->
                borrowing.returnBook(LocalDate.of(2026, 4, 29))
        )
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Return date cannot be before borrow date");
    }
}