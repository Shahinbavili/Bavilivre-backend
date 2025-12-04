package com.bavilivre.bavilivre_backend.application.usecase;

import com.bavilivre.bavilivre_backend.application.dto.BorrowedBooksDto;
import com.bavilivre.bavilivre_backend.domain.model.book.Book;
import com.bavilivre.bavilivre_backend.domain.model.book.BookId;
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
        BookId bookId = new BookId(7);
        Book book = new Book(bookId, lenderId);

        //When

        final BorrowBook borrowBook = new BorrowBook(lenderId, book, borrowerId);
        final BorrowedBooksDto borrowedBooksDto = borrowBook.fromAUserToAnotherUser();

        //Then

        Map<Integer, Integer> expected = new HashMap<>();
        expected.put(7, 1);
        assertThat(borrowedBooksDto.borrowedBookList()).isEqualTo(expected);
//        assertThat(borrower.borrowedBooks()).isEqualTo(lender.lentBooks());
    }


}
