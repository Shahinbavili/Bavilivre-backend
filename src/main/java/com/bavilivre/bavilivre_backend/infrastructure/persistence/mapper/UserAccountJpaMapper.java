package com.bavilivre.bavilivre_backend.infrastructure.persistence.mapper;

import com.bavilivre.bavilivre_backend.domain.model.user.UserId;
import com.bavilivre.bavilivre_backend.domain.model.useraccount.UserAccount;
import com.bavilivre.bavilivre_backend.infrastructure.persistence.entity.UserAccountJpaEntity;
import org.springframework.stereotype.Component;

@Component
public class UserAccountJpaMapper {

    public UserAccount toDomain(UserAccountJpaEntity entity) {
        return new UserAccount(
                new UserId(entity.getUserId()),
                entity.getEmail(),
                entity.getPasswordHash()
        );
    }

    public UserAccountJpaEntity toEntity(UserAccount userAccount) {
        return new UserAccountJpaEntity(
                userAccount.userId().value(),
                userAccount.email(),
                userAccount.passwordHash()
        );
    }
}