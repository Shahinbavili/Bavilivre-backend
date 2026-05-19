package com.bavilivre.bavilivre_backend.application.usecase;

import com.bavilivre.bavilivre_backend.application.port.BorrowingRepository;
import com.bavilivre.bavilivre_backend.domain.model.borrowing.Borrowing;
import com.bavilivre.bavilivre_backend.domain.model.borrowing.BorrowingId;

import java.time.LocalDate;

public class ReturnBook {

    private final BorrowingRepository borrowingRepository;

    public ReturnBook(BorrowingRepository borrowingRepository) {
        this.borrowingRepository = borrowingRepository;
    }

    public Borrowing returnBook(
            BorrowingId borrowingId,
            LocalDate returnedAt
    ) {
        Borrowing borrowing = borrowingRepository.findById(borrowingId)
                .orElseThrow(() -> new IllegalArgumentException("Borrowing not found"));

        borrowing.returnBook(returnedAt);

        return borrowingRepository.save(borrowing);
    }
}