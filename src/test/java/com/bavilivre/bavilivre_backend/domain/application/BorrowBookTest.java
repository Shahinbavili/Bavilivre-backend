package com.bavilivre.bavilivre_backend.domain.application;

import com.bavilivre.bavilivre_backend.domain.application.BorrowBook;
import com.bavilivre.bavilivre_backend.domain.model.BookId;
import com.bavilivre.bavilivre_backend.domain.model.User;
import com.bavilivre.bavilivre_backend.domain.model.UserId;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

public class BorrowBookTest {
    @Test
    void shouldAUserBorrowABookFromAnotherUser() {
        //Given
        UserId lender = new UserId(1);
        BookId bookId = new BookId(7);
        UserId borrowerId = new UserId(2);
        User borrower = new User(borrowerId);

        //When
        final BorrowBook borrowBook = new BorrowBook(lender, bookId, borrowerId);
        borrowBook.fromAUserToAnotherUser();

        //Then
        assertThat(borrower.hasBorrowed(bookId)).isTrue();
    }



}
