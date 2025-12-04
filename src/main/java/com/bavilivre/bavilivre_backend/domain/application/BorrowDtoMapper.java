package com.bavilivre.bavilivre_backend.domain.application;

import com.bavilivre.bavilivre_backend.domain.model.BookId;
import com.bavilivre.bavilivre_backend.domain.model.user.User;

import java.util.Map;
import java.util.stream.Collectors;

public class BorrowDtoMapper {
    public BorrowedBooksDto toDto(User borrower) {

        Map<Integer, Integer> borrowedBookList = borrower.borrowedBooks().stream()
                .collect(Collectors.toMap(
                        BookId::id,
                        BookId::ownerId
                ));

        return new BorrowedBooksDto(borrowedBookList);
    }
}
