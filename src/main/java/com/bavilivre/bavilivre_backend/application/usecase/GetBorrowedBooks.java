package com.bavilivre.bavilivre_backend.application.usecase;

import com.bavilivre.bavilivre_backend.application.mapper.BorrowDtoMapper;
import com.bavilivre.bavilivre_backend.application.port.BorrowingRepository;
import com.bavilivre.bavilivre_backend.domain.model.borrowing.Borrowing;
import com.bavilivre.bavilivre_backend.domain.model.user.UserId;
import com.bavilivre.bavilivre_backend.infrastructure.controller.response.BorrowedBooksDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetBorrowedBooks {

    private final BorrowingRepository borrowingRepository;
    private final BorrowDtoMapper borrowDtoMapper;

    public GetBorrowedBooks(BorrowingRepository borrowingRepository, BorrowDtoMapper borrowDtoMapper) {
        this.borrowingRepository = borrowingRepository;
        this.borrowDtoMapper = borrowDtoMapper;
    }

    public BorrowedBooksDto handle(Integer userId) {

        List<Borrowing> borrowings = borrowingRepository.findByBorrower_Id(new UserId(userId));

        return borrowDtoMapper.toDto(borrowings);
    }
}