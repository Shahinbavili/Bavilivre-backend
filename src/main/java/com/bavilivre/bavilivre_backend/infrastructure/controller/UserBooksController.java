package com.bavilivre.bavilivre_backend.infrastructure.controller;

import com.bavilivre.bavilivre_backend.infrastructure.controller.response.BorrowedBooksDto;
import com.bavilivre.bavilivre_backend.infrastructure.controller.response.LentBooksDto;
import com.bavilivre.bavilivre_backend.application.usecase.GetBorrowedBooks;
import com.bavilivre.bavilivre_backend.application.usecase.GetLentBooks;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserBooksController {

    private final GetBorrowedBooks getBorrowedBooks;
    private final GetLentBooks getLentBooks;

    public UserBooksController(GetBorrowedBooks getBorrowedBooks, GetLentBooks getLentBooks) {
        this.getBorrowedBooks = getBorrowedBooks;
        this.getLentBooks = getLentBooks;
    }

    @GetMapping("/{userId}/borrowed-books")
    public BorrowedBooksDto getBorrowedBooks(@PathVariable Integer userId) {

        return getBorrowedBooks.handle(userId);
    }

    @GetMapping("/{userId}/lent-books")
    public LentBooksDto getLentBooks(@PathVariable Integer userId) {
        return getLentBooks.handle(userId);
    }
}