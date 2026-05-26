package com.bavilivre.bavilivre_backend.application.usecase;

import com.bavilivre.bavilivre_backend.application.port.BorrowingRepository;
import com.bavilivre.bavilivre_backend.domain.exception.BorrowingNotFoundException;
import com.bavilivre.bavilivre_backend.domain.model.borrowing.Borrowing;
import com.bavilivre.bavilivre_backend.domain.model.borrowing.BorrowingId;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ReturnBook {

    private final BorrowingRepository borrowingRepository;

    public ReturnBook(BorrowingRepository borrowingRepository) {
        this.borrowingRepository = borrowingRepository;
    }

    public Borrowing handle(
            BorrowingId borrowingId,
            LocalDate returnedAt
    ) {
        Borrowing borrowing = borrowingRepository.findById(borrowingId)
                .orElseThrow(() -> new BorrowingNotFoundException(borrowingId));

        borrowing.returnBook(returnedAt);

        return borrowingRepository.save(borrowing);
    }
}