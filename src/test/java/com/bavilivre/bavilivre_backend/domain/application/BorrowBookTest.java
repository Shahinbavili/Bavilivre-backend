package com.bavilivre.bavilivre_backend.domain.application;

import com.bavilivre.bavilivre_backend.domain.model.BookId;
import com.bavilivre.bavilivre_backend.domain.model.user.User;
import com.bavilivre.bavilivre_backend.domain.model.user.UserId;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class BorrowBookTest {
    @Test
    void shouldAUserBorrowABookFromAnotherUser() {

        //Given

        UserId lenderId = new UserId(1);
        User lender = new User(lenderId);
        UserId borrowerId = new UserId(2);
//        User borrower = new User(borrowerId);
        BookId bookId = new BookId(7);

        //When

        final BorrowBook borrowBook = new BorrowBook(lenderId, bookId, borrowerId);
        final BorrowDto borrowDto = borrowBook.fromAUserToAnotherUser();

        //Then

        Set<Integer> expected = new HashSet<>();
        expected.add(7);
        assertThat(borrowDto.getBorrowedBookList()).isEqualTo(expected);
//        assertThat(borrower.borrowedBooks()).isEqualTo(lender.lentBooks());
    }


}
