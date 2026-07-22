package com.bavilivre.bavilivre_backend.application.mapper;

import com.bavilivre.bavilivre_backend.domain.model.borrowing.Borrowing;
import com.bavilivre.bavilivre_backend.infrastructure.controller.response.BorrowedBooksDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class BorrowDtoMapper {

    public BorrowedBooksDto toDto(List<Borrowing> borrowings) {

        Map<Integer, Integer> borrowedBookList = borrowings.stream()
                .filter(borrowing -> !borrowing.isReturned())
                .collect(Collectors.toMap(
                        borrowing -> borrowing.borrowerId().value(),
                        borrowing -> borrowing.lenderId().value()
                ));

        return new BorrowedBooksDto(borrowedBookList);
    }
}
