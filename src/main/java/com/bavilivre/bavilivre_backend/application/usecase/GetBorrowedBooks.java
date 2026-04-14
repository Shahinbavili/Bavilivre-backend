package com.bavilivre.bavilivre_backend.application.usecase;

import com.bavilivre.bavilivre_backend.application.dto.BorrowedBooksDto;
import com.bavilivre.bavilivre_backend.application.mapper.BorrowDtoMapper;
import org.springframework.stereotype.Service;

@Service
public class GetBorrowedBooks {

    private final BorrowDtoMapper borrowDtoMapper;

    public GetBorrowedBooks(BorrowDtoMapper borrowDtoMapper) {
        this.borrowDtoMapper = borrowDtoMapper;
    }

    public BorrowedBooksDto handle(Integer userId) {
        // temporaire
        return new BorrowedBooksDto(java.util.Map.of());
    }
}