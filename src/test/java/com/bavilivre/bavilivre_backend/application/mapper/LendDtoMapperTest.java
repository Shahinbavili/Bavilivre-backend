package com.bavilivre.bavilivre_backend.application.mapper;

import com.bavilivre.bavilivre_backend.domain.model.book.BookId;
import com.bavilivre.bavilivre_backend.domain.model.borrowing.Borrowing;
import com.bavilivre.bavilivre_backend.domain.model.borrowing.BorrowingId;
import com.bavilivre.bavilivre_backend.domain.model.user.UserId;
import com.bavilivre.bavilivre_backend.infrastructure.controller.response.LentBooksDto;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class LendDtoMapperTest {

    private final LendDtoMapper mapper = new LendDtoMapper();

    @Test
    void shouldMapActiveLentBooks() {

        Borrowing activeBorrowing = new Borrowing(
                new BorrowingId(1),
                new BookId(1),
                new UserId(2),
                new UserId(1),
                LocalDate.of(2026, 7, 1)
        );

        LentBooksDto result = mapper.toDto(List.of(activeBorrowing));

        assertThat(result.lentBookList())
                .isEqualTo(Map.of(1, 2));
    }

    @Test
    void shouldIgnoreReturnedBooks() {

        Borrowing returnedBorrowing = new Borrowing(
                new BorrowingId(1),
                new BookId(1),
                new UserId(2),
                new UserId(1),
                LocalDate.of(2026, 7, 1)
        );

        returnedBorrowing.returnBook(LocalDate.of(2026, 7, 1));

        LentBooksDto result = mapper.toDto(List.of(returnedBorrowing));

        assertThat(result.lentBookList())
                .isEmpty();
    }

    @Test
    void shouldReturnEmptyMapWhenNoBorrowingsExist() {

        LentBooksDto result = mapper.toDto(List.of());

        assertThat(result.lentBookList())
                .isEmpty();
    }
}