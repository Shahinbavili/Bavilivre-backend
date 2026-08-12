package com.bavilivre.bavilivre_backend.infrastructure.persistence.repository;

import com.bavilivre.bavilivre_backend.infrastructure.persistence.entity.BookJpaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface BookSpringDataRepository
        extends JpaRepository<BookJpaEntity, Integer>,
        JpaSpecificationExecutor<BookJpaEntity> {

    List<BookJpaEntity> findByOwner_IdAndArchived(
            Integer ownerId,
            boolean archived
    );

    Page<BookJpaEntity> findByOwner_IdAndArchived(
            Integer ownerId,
            boolean archived,
            Pageable pageable
    );
}