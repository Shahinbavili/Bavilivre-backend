package com.bavilivre.bavilivre_backend.infrastructure.controller;

import com.bavilivre.bavilivre_backend.application.dto.BorrowedBooksDto;
import com.bavilivre.bavilivre_backend.application.usecase.GetBorrowedBooks;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserBorrowController {

    private final GetBorrowedBooks getBorrowedBooks;

    public UserBorrowController(GetBorrowedBooks getBorrowedBooks) {
        this.getBorrowedBooks = getBorrowedBooks;
    }

    @GetMapping("/{userId}/borrowed-books")
    public BorrowedBooksDto getBorrowedBooks(@PathVariable Integer userId) {

        return getBorrowedBooks.handle(userId);
    }
}