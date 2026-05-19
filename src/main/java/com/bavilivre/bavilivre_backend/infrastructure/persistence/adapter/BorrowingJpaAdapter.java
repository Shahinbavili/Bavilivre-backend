package com.bavilivre.bavilivre_backend.infrastructure.persistence.adapter;

import com.bavilivre.bavilivre_backend.application.port.BorrowingRepository;
import com.bavilivre.bavilivre_backend.domain.model.borrowing.Borrowing;
import com.bavilivre.bavilivre_backend.domain.model.borrowing.BorrowingId;
import com.bavilivre.bavilivre_backend.domain.model.user.UserId;
import com.bavilivre.bavilivre_backend.infrastructure.persistence.mapper.BorrowingJpaMapper;
import com.bavilivre.bavilivre_backend.infrastructure.persistence.repository.BorrowingSpringDataRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class BorrowingJpaAdapter implements BorrowingRepository {

    private final BorrowingSpringDataRepository repository;
    private final BorrowingJpaMapper mapper;

    public BorrowingJpaAdapter(
            BorrowingSpringDataRepository repository,
            BorrowingJpaMapper mapper
    ) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Optional<Borrowing> findById(BorrowingId borrowingId) {
        return repository.findById(borrowingId.value())
                .map(mapper::toDomain);
    }

    @Override
    public List<Borrowing> findByBorrowerId(UserId borrowerId) {
        return repository.findByBorrowerId(borrowerId.value())
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public List<Borrowing> findByLenderId(UserId lenderId) {
        return repository.findByLenderId(lenderId.value())
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public Borrowing save(Borrowing borrowing) {

        var entity = mapper.toEntity(borrowing);

        var savedEntity = repository.save(entity);

        return mapper.toDomain(savedEntity);
    }
}