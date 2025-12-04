package com.bavilivre.bavilivre_backend.domain.application;

import com.bavilivre.bavilivre_backend.domain.model.BookId;
import com.bavilivre.bavilivre_backend.domain.model.user.UserId;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class BorrowBookTest {
    @Test
    void shouldAUserBorrowABookFromAnotherUser() {

        //Given

        UserId lenderId = new UserId(1);
        UserId borrowerId = new UserId(2);
        BookId bookId = new BookId(7, lenderId.value());

        //When

        final BorrowBook borrowBook = new BorrowBook(lenderId, bookId, borrowerId);
        final BorrowedBooksDto borrowedBooksDto = borrowBook.fromAUserToAnotherUser();

        //Then

        Map<Integer, Integer> expected = new HashMap<>();
        expected.put(7, 1);
        assertThat(borrowedBooksDto.borrowedBookList()).isEqualTo(expected);
//        assertThat(borrower.borrowedBooks()).isEqualTo(lender.lentBooks());
    }


}
