package com.bavilivre.bavilivre_backend.application.usecase;

import com.bavilivre.bavilivre_backend.application.mapper.LendDtoMapper;
import com.bavilivre.bavilivre_backend.domain.model.book.Book;
import com.bavilivre.bavilivre_backend.domain.model.book.BookId;
import com.bavilivre.bavilivre_backend.domain.model.user.User;
import com.bavilivre.bavilivre_backend.domain.model.user.UserId;
import com.bavilivre.bavilivre_backend.infrastructure.controller.response.LentBooksDto;
import org.springframework.stereotype.Service;

@Service
public class GetLentBooks {
    private final LendDtoMapper lendDtoMapper;

    public GetLentBooks(LendDtoMapper lendDtoMapper) {
        this.lendDtoMapper = lendDtoMapper;
    }

    public LentBooksDto handle(Integer userId) {
        User lender = new User(
                new UserId(userId),
                "User " + userId
        );

        //Temporary simulation
        lender.lend(new Book(
                new BookId(3),
                new UserId(userId),
                "Effective Java",
                "Joshua Bloch",
                "Best practices for modern Java development.",
                "en",
                "Programming",
                true,
                false
        ));
        lender.lend(new Book(
                new BookId(4),
                new UserId(userId),
                "Refactoring",
                "Martin Fowler",
                "Improving the design of existing code.",
                "en",
                "Software Engineering",
                true,
                false
        ));

        return lendDtoMapper.toDto(lender);
    }
}
