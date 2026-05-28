package com.bavilivre.bavilivre_backend.domain.exception;

import com.bavilivre.bavilivre_backend.domain.model.user.UserId;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(UserId id) {
        super("User " + id.value() + " not found");
    }
}