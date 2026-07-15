package com.bavilivre.bavilivre_backend.infrastructure.persistence.repository;

import com.bavilivre.bavilivre_backend.infrastructure.persistence.entity.BookJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface BookSpringDataRepository
        extends JpaRepository<BookJpaEntity, Integer>,
        JpaSpecificationExecutor<BookJpaEntity> {
}