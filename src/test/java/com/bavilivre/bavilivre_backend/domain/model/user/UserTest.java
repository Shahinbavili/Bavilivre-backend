package com.bavilivre.bavilivre_backend.domain.model.user;

import com.bavilivre.bavilivre_backend.domain.model.book.Book;
import com.bavilivre.bavilivre_backend.domain.model.book.BookId;
import com.bavilivre.bavilivre_backend.domain.model.ownership.OwnerId;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class UserTest {
    @Test
    public void shouldHaveAListOfBorrowedBooks() {
//       Given
        final UserId userId = new UserId(2);
        final User user = new User(userId);
        final OwnerId ownerId = new OwnerId(1);
        BookId bookId = new BookId(1);
        final Book book = new Book(bookId, user.id());

//       When
        user.borrow(book);

//       Then
        assertThat(user.hasBorrowed(book)).isTrue();
        assertThat(user.borrowedBooks()).contains(book);
//        user.borrowedBooks().forEach(System.out::println);
    }
}
