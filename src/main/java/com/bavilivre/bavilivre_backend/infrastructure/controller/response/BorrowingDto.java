package com.bavilivre.bavilivre_backend.infrastructure.controller.response;

import java.time.LocalDate;

public record BorrowingDto(
        Integer id,
        Integer bookId,
        Integer borrowerId,
        Integer lenderId,
        LocalDate borrowedAt,
        LocalDate returnedAt,
        boolean returned
) {
}