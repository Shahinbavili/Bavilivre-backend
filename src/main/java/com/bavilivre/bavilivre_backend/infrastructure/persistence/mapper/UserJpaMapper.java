package com.bavilivre.bavilivre_backend.infrastructure.persistence.mapper;

import com.bavilivre.bavilivre_backend.domain.model.user.User;
import com.bavilivre.bavilivre_backend.domain.model.user.UserId;
import com.bavilivre.bavilivre_backend.infrastructure.persistence.entity.UserJpaEntity;
import org.springframework.stereotype.Component;

@Component
public class UserJpaMapper {

    public User toDomain(UserJpaEntity entity) {
        return new User(
                new UserId(entity.getId()),
                entity.getDisplayName()
        );
    }

    public UserJpaEntity toEntity(User user) {
        Integer id = user.id() == null ? null : user.id().value();
        return new UserJpaEntity(
                id,
                user.displayName()
        );
    }
}