package com.bavilivre.bavilivre_backend.domain.application;

import com.bavilivre.bavilivre_backend.domain.model.BookId;
import com.bavilivre.bavilivre_backend.domain.model.user.User;

import java.util.Set;
import java.util.stream.Collectors;

public class BorrowBookMapper {
    public BorrowDto getBorrowDto(User borrower) {
        BorrowDto borrowDto = new BorrowDto();

        Set<Integer> borrowedBookIds = borrower.borrowedBooks().stream()
                .map(BookId::getId)
                .collect(Collectors.toSet());

        borrowDto.setBorrowedBookList(borrowedBookIds);
        return borrowDto;
    }
}
