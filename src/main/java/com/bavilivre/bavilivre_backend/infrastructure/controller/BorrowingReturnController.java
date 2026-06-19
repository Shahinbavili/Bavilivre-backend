package com.bavilivre.bavilivre_backend.infrastructure.controller;

import com.bavilivre.bavilivre_backend.application.usecase.ReturnBook;
import com.bavilivre.bavilivre_backend.domain.model.borrowing.Borrowing;
import com.bavilivre.bavilivre_backend.domain.model.borrowing.BorrowingId;
import com.bavilivre.bavilivre_backend.infrastructure.controller.response.BorrowingDto;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/borrowings")
public class BorrowingReturnController {

    private final ReturnBook returnBook;

    public BorrowingReturnController(ReturnBook returnBook) {
        this.returnBook = returnBook;
    }

    @PostMapping("/{borrowingId}/return")
    public BorrowingDto returnBook(
            @PathVariable Integer borrowingId
    ) {
        Borrowing borrowing = returnBook.handle(
                new BorrowingId(borrowingId),
                LocalDate.now()
        );

        return new BorrowingDto(
                borrowing.id().value(),
                borrowing.bookId().value(),
                borrowing.borrowerId().value(),
                borrowing.lenderId().value(),
                borrowing.borrowedAt(),
                borrowing.returnedAt(),
                borrowing.isReturned()
        );
    }
}