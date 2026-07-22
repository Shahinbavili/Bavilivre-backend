package com.bavilivre.bavilivre_backend.application.usecase;

import com.bavilivre.bavilivre_backend.application.mapper.LendDtoMapper;
import com.bavilivre.bavilivre_backend.application.port.BorrowingRepository;
import com.bavilivre.bavilivre_backend.domain.model.borrowing.Borrowing;
import com.bavilivre.bavilivre_backend.domain.model.user.UserId;
import com.bavilivre.bavilivre_backend.infrastructure.controller.response.LentBooksDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetLentBooks {

    private final BorrowingRepository borrowingRepository;
    private final LendDtoMapper lendDtoMapper;

    public GetLentBooks(BorrowingRepository borrowingRepository, LendDtoMapper lendDtoMapper) {
        this.borrowingRepository = borrowingRepository;
        this.lendDtoMapper = lendDtoMapper;
    }

    public LentBooksDto handle(Integer userId) {

        List<Borrowing> borrowings = borrowingRepository.findByLender_Id(new UserId(userId));

        return lendDtoMapper.toDto(borrowings);
    }
}
