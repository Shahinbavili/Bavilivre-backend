package com.bavilivre.bavilivre_backend.infrastructure.controller;

import com.bavilivre.bavilivre_backend.infrastructure.controller.response.BookDto;
import com.bavilivre.bavilivre_backend.application.usecase.GetBookById;
import com.bavilivre.bavilivre_backend.domain.model.book.Book;
import com.bavilivre.bavilivre_backend.domain.model.book.BookId;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private final GetBookById getBookById;

    public BookController(GetBookById getBookById) {
        this.getBookById = getBookById;
    }

    @GetMapping
    public List<String> getBooks() {
        return List.of(
                "Clean Code",
                "Domain-Driven Design",
                "Effective Java"
        );
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