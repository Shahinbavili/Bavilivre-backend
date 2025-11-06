package com.bavilivre.bavilivre_backend.domain.application;

import com.bavilivre.bavilivre_backend.domain.model.BookId;
import com.bavilivre.bavilivre_backend.domain.model.user.User;

import java.util.Map;
import java.util.stream.Collectors;

public class BorrowBookMapper {
    public BorrowDto getBorrowDto(User borrower) {
        BorrowDto borrowDto = new BorrowDto();

        Map<Integer, Integer> borrowedBookIds = borrower.borrowedBooks().stream()
                .collect(Collectors.toMap(
                        BookId::getId,
                        BookId::getOwnerId
                ));
        borrowDto.setBorrowedBookList(borrowedBookIds);
        return borrowDto;
    }
}
