package com.bavilivre.bavilivre_backend.infrastructure.persistence.repository;

import com.bavilivre.bavilivre_backend.infrastructure.persistence.entity.BorrowingJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BorrowingSpringDataRepository extends JpaRepository<BorrowingJpaEntity, Integer> {
    List<BorrowingJpaEntity> findByBorrowerId(Integer borrowerId);

    List<BorrowingJpaEntity> findByLenderId(Integer lenderId);
}