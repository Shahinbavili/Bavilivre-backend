package com.bavilivre.bavilivre_backend.application.usecase;

import com.bavilivre.bavilivre_backend.application.mapper.LendDtoMapper;
import com.bavilivre.bavilivre_backend.application.port.BorrowingRepository;
import com.bavilivre.bavilivre_backend.domain.model.book.BookId;
import com.bavilivre.bavilivre_backend.domain.model.borrowing.Borrowing;
import com.bavilivre.bavilivre_backend.domain.model.borrowing.BorrowingId;
import com.bavilivre.bavilivre_backend.domain.model.user.UserId;
import com.bavilivre.bavilivre_backend.infrastructure.controller.response.LentBooksDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetLentBooksTest {

    @Mock
    private BorrowingRepository borrowingRepository;

    @Mock
    private LendDtoMapper lendDtoMapper;

    @InjectMocks
    private GetLentBooks getLentBooks;

    @Test
    void shouldReturnLentBooksForUser() {
        UserId lenderId = new UserId(1);

        List<Borrowing> borrowings = List.of(
                new Borrowing(
                        new BorrowingId(1),
                        new BookId(1),
                        new UserId(2),
                        lenderId,
                        LocalDate.of(2026, 7, 1)
                )
        );

        LentBooksDto expectedDto = new LentBooksDto(
                Map.of(1, 2)
        );

        when(borrowingRepository.findByLender_Id(lenderId))
                .thenReturn(borrowings);

        when(lendDtoMapper.toDto(borrowings))
                .thenReturn(expectedDto);

        LentBooksDto result = getLentBooks.handle(1);

        assertThat(result)
                .isEqualTo(expectedDto);

        verify(borrowingRepository)
                .findByLender_Id(lenderId);

        verify(lendDtoMapper)
                .toDto(borrowings);
    }
}