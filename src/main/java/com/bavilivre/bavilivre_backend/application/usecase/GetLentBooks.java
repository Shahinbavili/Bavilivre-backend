package com.bavilivre.bavilivre_backend.application.usecase;

import com.bavilivre.bavilivre_backend.application.dto.LentBooksDto;
import com.bavilivre.bavilivre_backend.application.mapper.LendDtoMapper;
import com.bavilivre.bavilivre_backend.domain.model.book.Book;
import com.bavilivre.bavilivre_backend.domain.model.book.BookId;
import com.bavilivre.bavilivre_backend.domain.model.user.User;
import com.bavilivre.bavilivre_backend.domain.model.user.UserId;
import org.springframework.stereotype.Service;

@Service
public class GetLentBooks {
    private final LendDtoMapper lendDtoMapper;

    public GetLentBooks(LendDtoMapper lendDtoMapper) {
        this.lendDtoMapper = lendDtoMapper;
    }

    public LentBooksDto handle(Integer userId) {
        User lender = new User(new UserId(userId));

        //Temporary simulation
        lender.lend(new Book(new BookId(3), new UserId(userId)));
        lender.lend(new Book(new BookId(4), new UserId(userId)));

        return lendDtoMapper.toDto(lender);
    }
}
