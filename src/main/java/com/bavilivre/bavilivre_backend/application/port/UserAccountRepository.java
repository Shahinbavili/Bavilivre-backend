package com.bavilivre.bavilivre_backend.application.port;

import com.bavilivre.bavilivre_backend.domain.model.useraccount.UserAccount;

import java.util.Optional;

public interface UserAccountRepository {

    Optional<UserAccount> findByEmail(String email);

    boolean existsByEmail(String email);

    UserAccount save(UserAccount userAccount);
}