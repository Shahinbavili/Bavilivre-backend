package com.bavilivre.bavilivre_backend.infrastructure.controller;

import com.bavilivre.bavilivre_backend.application.query.BookFilter;
import com.bavilivre.bavilivre_backend.application.query.PageResult;
import com.bavilivre.bavilivre_backend.application.usecase.*;
import com.bavilivre.bavilivre_backend.domain.model.book.Book;
import com.bavilivre.bavilivre_backend.domain.model.book.BookId;
import com.bavilivre.bavilivre_backend.domain.model.useraccount.UserAccount;
import com.bavilivre.bavilivre_backend.infrastructure.controller.request.AddBookRequest;
import com.bavilivre.bavilivre_backend.infrastructure.controller.request.UpdateBookRequest;
import com.bavilivre.bavilivre_backend.infrastructure.controller.response.BookDto;
import com.bavilivre.bavilivre_backend.infrastructure.controller.response.PageResponse;
import com.bavilivre.bavilivre_backend.infrastructure.persistence.mapper.BookDtoMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final GetBookById getBookById;
    private final GetAvailableBooks getAvailableBooks;
    private final AddBook addBook;
    private final BookDtoMapper bookDtoMapper;
    private final GetUserByEmail getUserByEmail;
    private final UpdateBook updateBook;
    private final ArchiveBook archiveBook;
    private final SearchBooks searchBooks;
    private final GetFilteredBooks getFilteredBooks;

    public BookController(GetBookById getBookById, GetAvailableBooks getAvailableBooks, AddBook addBook, BookDtoMapper bookDtoMapper, GetUserByEmail getUserByEmail, UpdateBook updateBook, ArchiveBook archiveBook, SearchBooks searchBooks, GetFilteredBooks getFilteredBooks) {
        this.getBookById = getBookById;
        this.getAvailableBooks = getAvailableBooks;
        this.addBook = addBook;
        this.bookDtoMapper = bookDtoMapper;
        this.getUserByEmail = getUserByEmail;
        this.updateBook = updateBook;
        this.archiveBook = archiveBook;
        this.searchBooks = searchBooks;
        this.getFilteredBooks = getFilteredBooks;
    }

    @GetMapping("/available")
    public List<BookDto> getAvailableBooks() {
        return getAvailableBooks.handle()
                .stream()
                .map(bookDtoMapper::toDto)
                .toList();
    }

    @GetMapping("/search")
    public List<BookDto> searchBook
            (
                    @RequestParam String q
            ) {
        return searchBooks.handle(q)
                .stream()
                .map(bookDtoMapper::toDto)
                .toList();
    }

    @GetMapping("/{id}")
    public BookDto getBook(
            @PathVariable Integer id
    ) {
        Book book = getBookById.handle(
                new BookId(id)
        );

        return bookDtoMapper.toDto(book);
    }

    @PostMapping
    public BookDto addBook(
            @RequestBody AddBookRequest request
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        UserAccount currentUser = getUserByEmail.handle(email);

        Book createdBook = addBook.handle(
                currentUser.userId(),
                request.title(),
                request.author(),
                request.description(),
                request.language(),
                request.category()
        );

        return bookDtoMapper.toDto(createdBook);
    }

    @PutMapping("/{id}")
    public BookDto updateBook(
            @PathVariable Integer id,
            @RequestBody UpdateBookRequest request
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        UserAccount currentUser = getUserByEmail.handle(email);

        Book updatedBook = updateBook.handle(
                new BookId(id),
                currentUser.userId(),
                request.title(),
                request.author(),
                request.description(),
                request.language(),
                request.category()
        );

        return bookDtoMapper.toDto(updatedBook);
    }

    @PatchMapping("/{id}/archive")
    public BookDto archiveBook(
            @PathVariable Integer id
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        UserAccount currentUser = getUserByEmail.handle(email);

        Book archivedBook = archiveBook.handle(
                new BookId(id),
                currentUser.userId()
        );

        return bookDtoMapper.toDto(archivedBook);
    }

    @GetMapping
    public PageResponse<BookDto> getBooks(
            @RequestParam(required = false) String language,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) Boolean available,
            @RequestParam(required = false) String sort,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size
    ) {
        BookFilter filter = new BookFilter(language, category, available, sort, page, size);

        PageResult<Book> result = getFilteredBooks.handle(filter);

        List<BookDto> content = result.content()
                .stream()
                .map(bookDtoMapper::toDto)
                .toList();

        return new PageResponse<>(
                content,
                result.page(),
                result.size(),
                result.totalElements(),
                result.totalPages()
        );
    }

}