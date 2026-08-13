package com.bavilivre.bavilivre_backend.application.usecase;

import com.bavilivre.bavilivre_backend.application.port.BookRepository;
import com.bavilivre.bavilivre_backend.application.query.PageResult;
import com.bavilivre.bavilivre_backend.domain.model.book.Book;
import com.bavilivre.bavilivre_backend.domain.model.user.UserId;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class GetMyBooksTest {

    private final BookRepository bookRepository = mock(BookRepository.class);
    private final GetMyBooks getMyBooks = new GetMyBooks(bookRepository);

    @Test
    void shouldReturnOnlyCurrentUsersActiveBooks() {
        UserId currentUserId = new UserId(1);

        PageResult<Book> expectedResult = new PageResult<>(
                List.of(),
                0,
                0,
                0,
                12
        );

        when(bookRepository.findByOwnerIdAndArchived(
                currentUserId,
                false,
                0,
                12
        )).thenReturn(expectedResult);

        PageResult<Book> result = getMyBooks.handle(
                currentUserId,
                0,
                12
        );

        assertThat(result).isSameAs(expectedResult);

        verify(bookRepository).findByOwnerIdAndArchived(
                currentUserId,
                false,
                0,
                12
        );

        verifyNoMoreInteractions(bookRepository);
    }
}