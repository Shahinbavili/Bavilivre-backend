package com.bavilivre.bavilivre_backend.application.usecase;

import com.bavilivre.bavilivre_backend.application.port.BorrowingRepository;
import com.bavilivre.bavilivre_backend.domain.model.book.BookId;
import com.bavilivre.bavilivre_backend.domain.model.borrowing.Borrowing;
import com.bavilivre.bavilivre_backend.domain.model.borrowing.BorrowingId;
import com.bavilivre.bavilivre_backend.domain.model.user.UserId;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ReturnBookTest {

    @Test
    void should_return_a_borrowed_book() {
        BorrowingRepository borrowingRepository = mock(BorrowingRepository.class);

        ReturnBook returnBook = new ReturnBook(borrowingRepository);

        BorrowingId borrowingId = new BorrowingId(100);
        LocalDate borrowedAt = LocalDate.of(2026, 5, 19);
        LocalDate returnedAt = LocalDate.of(2026, 5, 24);

        Borrowing borrowing = new Borrowing(
                borrowingId,
                new BookId(10),
                new UserId(2),
                new UserId(1),
                borrowedAt
        );

        when(borrowingRepository.findById(borrowingId))
                .thenReturn(Optional.of(borrowing));

        when(borrowingRepository.save(any(Borrowing.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Borrowing returnedBorrowing = returnBook.returnBook(
                borrowingId,
                returnedAt
        );

        assertThat(returnedBorrowing.returnedAt()).isEqualTo(returnedAt);
        assertThat(returnedBorrowing.isReturned()).isTrue();

        verify(borrowingRepository).findById(borrowingId);
        verify(borrowingRepository).save(any(Borrowing.class));
    }
}