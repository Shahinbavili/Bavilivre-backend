package com.bavilivre.bavilivre_backend.application.port;

import com.bavilivre.bavilivre_backend.domain.model.user.User;
import com.bavilivre.bavilivre_backend.domain.model.user.UserId;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    Optional<User> findById(UserId userId);

    List<User> findAll();

    User save(User user);
}