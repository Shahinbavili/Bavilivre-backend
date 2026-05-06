package com.bavilivre.bavilivre_backend.infrastructure.persistence.repository;

import com.bavilivre.bavilivre_backend.infrastructure.persistence.entity.UserJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserSpringDataRepository extends JpaRepository<UserJpaEntity, Integer> {
}