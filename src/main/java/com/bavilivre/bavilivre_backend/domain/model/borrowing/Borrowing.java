package com.bavilivre.bavilivre_backend.domain.model.borrowing;

import com.bavilivre.bavilivre_backend.domain.model.book.BookId;
import com.bavilivre.bavilivre_backend.domain.model.user.UserId;

import java.time.LocalDate;

public class Borrowing {
    private final BorrowingId id;
    private final BookId bookId;
    private final UserId borrowerId;
    private final UserId lenderId;
    private final LocalDate borrowedAt;
    private LocalDate returnedAt;

    public Borrowing(
            BorrowingId id,
            BookId bookId,
            UserId borrowerId,
            UserId lenderId,
            LocalDate borrowedAt
    ) {
        this.id = id;
        this.bookId = bookId;
        this.borrowerId = borrowerId;
        this.lenderId = lenderId;
        this.borrowedAt = borrowedAt;
    }

    public BorrowingId id() {
        return id;
    }

    public BookId bookId() {
        return bookId;
    }

    public UserId borrowerId() {
        return borrowerId;
    }

    public UserId lenderId() {
        return lenderId;
    }

    public LocalDate borrowedAt() {
        return borrowedAt;
    }

    public LocalDate returnedAt() {
        return returnedAt;
    }

    public boolean isReturned() {
        return returnedAt != null;
    }

    public void returnBook(LocalDate returnedAt) {
        if (isReturned()) {
            throw new IllegalStateException("Book already returned");
        }
        if (returnedAt.isBefore(borrowedAt)) {
            throw new IllegalArgumentException("Return date cannot be before borrow date");
        }
        this.returnedAt = returnedAt;
    }
}
