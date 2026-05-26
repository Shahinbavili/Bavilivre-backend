package com.bavilivre.bavilivre_backend.infrastructure.controller;

import com.bavilivre.bavilivre_backend.application.usecase.BorrowBook;
import com.bavilivre.bavilivre_backend.domain.model.book.BookId;
import com.bavilivre.bavilivre_backend.domain.model.borrowing.Borrowing;
import com.bavilivre.bavilivre_backend.domain.model.user.UserId;
import com.bavilivre.bavilivre_backend.infrastructure.controller.request.BorrowBookRequest;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/books")
public class BorrowingController {

    private final BorrowBook borrowBook;

    public BorrowingController(BorrowBook borrowBook) {
        this.borrowBook = borrowBook;
    }

    @PostMapping("/{bookId}/borrow")
    public Borrowing borrowBook(
            @PathVariable Integer bookId,
            @RequestBody BorrowBookRequest request
    ) {
        return borrowBook.borrow(
                null,
                new BookId(bookId),
                new UserId(request.borrowerId()),
                LocalDate.now()
        );
    }


}