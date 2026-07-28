package com.bavilivre.bavilivre_backend.infrastructure.controller;

import com.bavilivre.bavilivre_backend.application.usecase.*;
import com.bavilivre.bavilivre_backend.domain.model.book.Book;
import com.bavilivre.bavilivre_backend.domain.model.book.BookId;
import com.bavilivre.bavilivre_backend.domain.model.user.UserId;
import com.bavilivre.bavilivre_backend.domain.model.useraccount.UserAccount;
import com.bavilivre.bavilivre_backend.infrastructure.controller.response.BookDto;
import com.bavilivre.bavilivre_backend.infrastructure.persistence.mapper.BookDtoMapper;
import com.bavilivre.bavilivre_backend.infrastructure.security.JwtAuthenticationFilter;
import com.bavilivre.bavilivre_backend.infrastructure.security.JwtService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BookController.class)
@AutoConfigureMockMvc(addFilters = false)
class BookControllerTest {

    private static final String CURRENT_USER_EMAIL =
            "shahinbavili@gmail.com";

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private JwtService jwtService;

    @MockitoBean
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @MockitoBean
    private GetBookById getBookById;

    @MockitoBean
    private GetAvailableBooks getAvailableBooks;

    @MockitoBean
    private AddBook addBook;

    @MockitoBean
    private BookDtoMapper bookDtoMapper;

    @MockitoBean
    private GetUserByEmail getUserByEmail;

    @MockitoBean
    private UpdateBook updateBook;

    @MockitoBean
    private ArchiveBook archiveBook;

    @MockitoBean
    private SearchBooks searchBooks;

    @MockitoBean
    private GetFilteredBooks getFilteredBooks;

    @MockitoBean
    private GetArchivedBooks getArchivedBooks;

    @MockitoBean
    private UnarchiveBook unarchiveBook;

    @Test
    @WithMockUser(username = CURRENT_USER_EMAIL)
    void shouldReturnArchivedBooksForCurrentUser() throws Exception {
        UserId currentUserId = new UserId(1);

        UserAccount currentUser = mock(
                UserAccount.class
        );

        Book archivedBook = new Book(
                new BookId(1),
                currentUserId,
                "Clean Code",
                "Robert C. Martin",
                "A practical guide to clean code.",
                "en",
                "Software Engineering",
                false,
                true,
                LocalDateTime.of(2026, 7, 1, 10, 0)
        );

        BookDto archivedBookDto = new BookDto(
                1,
                1,
                "Clean Code",
                "Robert C. Martin",
                "A practical guide to clean code.",
                "en",
                "Software Engineering",
                false,
                true,
                LocalDateTime.of(2026, 7, 1, 10, 0)
        );

        when(currentUser.userId())
                .thenReturn(currentUserId);

        when(getUserByEmail.handle(CURRENT_USER_EMAIL))
                .thenReturn(currentUser);

        when(getArchivedBooks.handle(currentUserId))
                .thenReturn(List.of(archivedBook));

        when(bookDtoMapper.toDto(archivedBook))
                .thenReturn(archivedBookDto);

        mockMvc.perform(get("/api/books/archived"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].ownerId").value(1))
                .andExpect(jsonPath("$[0].title").value("Clean Code"))
                .andExpect(jsonPath("$[0].archived").value(true));

        verify(getUserByEmail)
                .handle(CURRENT_USER_EMAIL);

        verify(getArchivedBooks)
                .handle(currentUserId);

        verify(bookDtoMapper)
                .toDto(archivedBook);
    }

    @Test
    @WithMockUser(username = CURRENT_USER_EMAIL)
    void shouldReturnEmptyListWhenCurrentUserHasNoArchivedBooks()
            throws Exception {

        UserId currentUserId = new UserId(1);

        UserAccount currentUser = mock(
                UserAccount.class
        );

        when(currentUser.userId())
                .thenReturn(currentUserId);

        when(getUserByEmail.handle(CURRENT_USER_EMAIL))
                .thenReturn(currentUser);

        when(getArchivedBooks.handle(currentUserId))
                .thenReturn(List.of());

        mockMvc.perform(get("/api/books/archived"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));

        verify(getUserByEmail)
                .handle(CURRENT_USER_EMAIL);

        verify(getArchivedBooks)
                .handle(currentUserId);
    }

    @Test
    @WithMockUser(username = CURRENT_USER_EMAIL)
    void shouldUnarchiveBookForCurrentOwner() throws Exception {
        UserId currentUserId = new UserId(1);
        BookId bookId = new BookId(1);

        UserAccount currentUser = mock(
                UserAccount.class
        );

        Book unarchivedBook = new Book(
                bookId,
                currentUserId,
                "Clean Code",
                "Robert C. Martin",
                "A practical guide to clean code.",
                "en",
                "Software Engineering",
                true,
                false,
                LocalDateTime.of(2026, 7, 1, 10, 0)
        );

        BookDto unarchivedBookDto = new BookDto(
                1,
                1,
                "Clean Code",
                "Robert C. Martin",
                "A practical guide to clean code.",
                "en",
                "Software Engineering",
                true,
                false,
                LocalDateTime.of(2026, 7, 1, 10, 0)
        );

        when(currentUser.userId())
                .thenReturn(currentUserId);

        when(getUserByEmail.handle(CURRENT_USER_EMAIL))
                .thenReturn(currentUser);

        when(unarchiveBook.handle(bookId, currentUserId))
                .thenReturn(unarchivedBook);

        when(bookDtoMapper.toDto(unarchivedBook))
                .thenReturn(unarchivedBookDto);

        mockMvc.perform(
                        patch(
                                "/api/books/{id}/unarchive",
                                bookId.value()
                        )
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.ownerId").value(1))
                .andExpect(jsonPath("$.title").value("Clean Code"))
                .andExpect(jsonPath("$.archived").value(false));

        verify(getUserByEmail)
                .handle(CURRENT_USER_EMAIL);

        verify(unarchiveBook)
                .handle(bookId, currentUserId);

        verify(bookDtoMapper)
                .toDto(unarchivedBook);
    }
}