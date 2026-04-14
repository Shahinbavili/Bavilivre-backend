package com.bavilivre.bavilivre_backend.application.mapper;

import com.bavilivre.bavilivre_backend.application.dto.BorrowedBooksDto;
import com.bavilivre.bavilivre_backend.domain.model.user.User;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.stream.Collectors;

@Component
public class BorrowDtoMapper {

    public BorrowedBooksDto toDto(User borrower) {

        Map<Integer, Integer> borrowedBookList = borrower.borrowedBooks().stream()
                .collect(Collectors.toMap(
                        book -> book.id().value(),
                        book -> book.ownerId().value()
                ));

        return new BorrowedBooksDto(borrowedBookList);
    }
}
