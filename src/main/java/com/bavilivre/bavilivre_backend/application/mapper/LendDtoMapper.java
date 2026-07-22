package com.bavilivre.bavilivre_backend.application.mapper;

import com.bavilivre.bavilivre_backend.domain.model.borrowing.Borrowing;
import com.bavilivre.bavilivre_backend.infrastructure.controller.response.LentBooksDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class LendDtoMapper {

    public LentBooksDto toDto(List<Borrowing> borrowings) {

        Map<Integer, Integer> lentBookList = borrowings.stream()
                .filter(borrowing -> !borrowing.isReturned())
                .collect(Collectors.toMap(
                        borrowing -> borrowing.bookId().value(),
                        borrowing -> borrowing.borrowerId().value()
                ));

        return new LentBooksDto(lentBookList);
    }

}
