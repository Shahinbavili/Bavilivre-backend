package com.bavilivre.bavilivre_backend.infrastructure.persistence.mapper;

import com.bavilivre.bavilivre_backend.domain.model.user.User;
import com.bavilivre.bavilivre_backend.domain.model.user.UserId;
import com.bavilivre.bavilivre_backend.infrastructure.persistence.entity.UserJpaEntity;

public class UserJpaMapper {

    public User toDomain(UserJpaEntity entity) {
        return new User(
                new UserId(entity.getId())
        );
    }

    public UserJpaEntity toEntity(UserId userId) {
        return new UserJpaEntity(
                userId.value()
        );
    }
}