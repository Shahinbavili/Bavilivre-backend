package com.bavilivre.bavilivre_backend.application.port;

import com.bavilivre.bavilivre_backend.domain.model.user.User;
import com.bavilivre.bavilivre_backend.domain.model.user.UserId;

import java.util.Optional;

public interface UserRepository {

    Optional<User> findById(UserId userId);

    User save(User user);
}