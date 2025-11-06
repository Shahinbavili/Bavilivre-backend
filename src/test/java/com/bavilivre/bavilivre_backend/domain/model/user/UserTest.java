package com.bavilivre.bavilivre_backend.domain.model.user;

import com.bavilivre.bavilivre_backend.domain.model.BookId;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class UserTest {
    @Test
    public void shouldHaveAListOfBorrowedBooks() {
//       Given
        final UserId userId = new UserId(2);
        final User user = new User(userId);
        final BookId bookId = new BookId(7, 2);

//       When
        user.borrow(bookId);

//       Then
        assertThat(user.hasBorrowed(bookId)).isTrue();
        assertThat(user.borrowedBooks()).contains(bookId);
//        user.borrowedBooks().forEach(System.out::println);
    }
}
