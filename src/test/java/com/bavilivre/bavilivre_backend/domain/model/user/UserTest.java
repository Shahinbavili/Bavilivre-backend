package com.bavilivre.bavilivre_backend.domain.model.user;

import com.bavilivre.bavilivre_backend.domain.model.book.Book;
import com.bavilivre.bavilivre_backend.domain.model.book.BookId;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;


public class UserTest {

    private static final LocalDateTime CREATED_AT =
            LocalDateTime.of(2026, 1, 1, 10, 0);

    @Test
    public void shouldHaveAListOfBorrowedBooks() {
//       Given
        final UserId userId = new UserId(2);
        final User user = new User(userId, "Shahin");
        BookId bookId = new BookId(1);
        final Book book = new Book(bookId,
                user.id(),
                "Clean Architecture",
                "Robert C. Martin",
                "A guide to software architecture and clean boundaries.",
                "en",
                "Software Engineering",
                true,
                false,
                CREATED_AT
        );

//       When
        user.borrow(book);

//       Then
        assertThat(user.hasBorrowed(book)).isTrue();
        assertThat(user.borrowedBooks()).contains(book);
//        user.borrowedBooks().forEach(System.out::println);
    }
}
