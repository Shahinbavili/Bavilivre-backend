package com.bavilivre.bavilivre_backend.domain.model.useraccount;

import com.bavilivre.bavilivre_backend.domain.model.user.UserId;

public record UserAccount(
        UserId userId,
        String email,
        String passwordHash
) {
}