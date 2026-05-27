package com.bavilivre.bavilivre_backend.infrastructure.controller;

import com.bavilivre.bavilivre_backend.application.usecase.GetAvailableBooks;
import com.bavilivre.bavilivre_backend.application.usecase.GetBookById;
import com.bavilivre.bavilivre_backend.domain.model.book.Book;
import com.bavilivre.bavilivre_backend.domain.model.book.BookId;
import com.bavilivre.bavilivre_backend.infrastructure.controller.response.BookDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private final GetBookById getBookById;
    private final GetAvailableBooks getAvailableBooks;

    public BookController(GetBookById getBookById, GetAvailableBooks getAvailableBooks) {
        this.getBookById = getBookById;
        this.getAvailableBooks = getAvailableBooks;
    }

    @GetMapping("/available")
    public List<BookDto> getAvailableBooks() {
        return getAvailableBooks.handle()
                .stream()
                .map(book -> new BookDto(
                        book.id().value(),
                        book.ownerId().value()
                ))
                .toList();
    }

    @GetMapping("/{id}")
    public BookDto getBook(
            @PathVariable Integer id
    ) {
        Book book = getBookById.handle(
                new BookId(id)
        );

        return new BookDto(
                book.id().value(),
                book.ownerId().value()
        );
    }

}