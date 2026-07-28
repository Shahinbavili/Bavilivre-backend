package com.bavilivre.bavilivre_backend.application.usecase;

import com.bavilivre.bavilivre_backend.application.port.BookRepository;
import com.bavilivre.bavilivre_backend.domain.exception.BookNotFoundException;
import com.bavilivre.bavilivre_backend.domain.exception.UserIsNotBookOwnerException;
import com.bavilivre.bavilivre_backend.domain.model.book.Book;
import com.bavilivre.bavilivre_backend.domain.model.book.BookId;
import com.bavilivre.bavilivre_backend.domain.model.user.UserId;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UnarchiveBookTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private UnarchiveBook unarchiveBook;

    @Test
    void shouldUnarchiveBook() {

        UserId ownerId = new UserId(1);
        BookId bookId = new BookId(1);

        Book archivedBook = new Book(
                bookId,
                ownerId,
                "Clean Code",
                "Robert C. Martin",
                "Description",
                "en",
                "Software Engineering",
                true,
                true,
                LocalDateTime.now()
        );

        Book unarchivedBook = archivedBook.unarchive();

        when(bookRepository.findById(bookId))
                .thenReturn(Optional.of(archivedBook));

        when(bookRepository.save(unarchivedBook))
                .thenReturn(unarchivedBook);

        Book result = unarchiveBook.handle(bookId, ownerId);

        assertThat(result).isEqualTo(unarchivedBook);

        verify(bookRepository).findById(bookId);

        verify(bookRepository).save(unarchivedBook);
    }

    @Test
    void shouldThrowWhenBookDoesNotExist() {

        BookId bookId = new BookId(1);
        UserId currentUserId = new UserId(1);

        when(bookRepository.findById(bookId))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() ->
                unarchiveBook.handle(bookId, currentUserId)
        )
                .isInstanceOf(BookNotFoundException.class);

        verify(bookRepository).findById(bookId);

        verify(bookRepository, never()).save(any());
    }

    @Test
    void shouldThrowWhenCurrentUserIsNotOwner() {

        BookId bookId = new BookId(1);

        UserId ownerId = new UserId(1);
        UserId currentUserId = new UserId(2);

        Book archivedBook = new Book(
                bookId,
                ownerId,
                "Clean Code",
                "Robert C. Martin",
                "Description",
                "en",
                "Software Engineering",
                true,
                true,
                LocalDateTime.now()
        );

        when(bookRepository.findById(bookId))
                .thenReturn(Optional.of(archivedBook));

        assertThatThrownBy(() ->
                unarchiveBook.handle(bookId, currentUserId)
        )
                .isInstanceOf(UserIsNotBookOwnerException.class);

        verify(bookRepository)
                .findById(bookId);

        verify(bookRepository, never())
                .save(any());
    }
}