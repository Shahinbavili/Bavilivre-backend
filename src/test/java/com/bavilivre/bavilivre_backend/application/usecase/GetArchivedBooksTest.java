package com.bavilivre.bavilivre_backend.application.usecase;

import com.bavilivre.bavilivre_backend.application.port.BookRepository;
import com.bavilivre.bavilivre_backend.domain.model.book.Book;
import com.bavilivre.bavilivre_backend.domain.model.book.BookId;
import com.bavilivre.bavilivre_backend.domain.model.user.UserId;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetArchivedBooksTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private GetArchivedBooks getArchivedBooks;

    @Test
    void shouldReturnArchivedBooksForCurrentUser() {
        UserId currentUserId = new UserId(1);

        List<Book> archivedBooks = List.of(
                new Book(
                        new BookId(1),
                        currentUserId,
                        "Clean Code",
                        "Robert C. Martin",
                        "Description",
                        "en",
                        "Software Engineering",
                        false,
                        true,
                        null
                )
        );

        when(bookRepository.findByOwnerIdAndArchived(currentUserId, true))
                .thenReturn(archivedBooks);

        List<Book> result = getArchivedBooks.handle(currentUserId);

        assertThat(result).isEqualTo(archivedBooks);

        verify(bookRepository).findByOwnerIdAndArchived(currentUserId, true);
    }

    @Test
    void shouldReturnEmptyListWhenUserHasNoArchivedBooks() {
        UserId currentUserId = new UserId(1);

        when(bookRepository.findByOwnerIdAndArchived(currentUserId, true))
                .thenReturn(List.of());

        List<Book> result = getArchivedBooks.handle(currentUserId);

        assertThat(result).isEmpty();

        verify(bookRepository).findByOwnerIdAndArchived(currentUserId, true);
    }
}