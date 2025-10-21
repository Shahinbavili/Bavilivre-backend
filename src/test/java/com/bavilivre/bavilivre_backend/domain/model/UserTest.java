package com.bavilivre.bavilivre_backend.domain.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UserTest {
    @Test
    public void shouldHaveAListOfBorrowedBooks() {
//       Given
        final UserId userId = new UserId(2);
        final User user = new User(userId);
        final BookId bookId = new BookId(7);

//       When
        user.barrow(bookId);

//       Then
        //verifier que id de livre exist dans la liste
        assertThat(user.hasBorrowed(bookId)).isTrue();
    }
}
