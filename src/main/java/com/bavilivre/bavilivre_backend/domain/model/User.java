package com.bavilivre.bavilivre_backend.domain.model;

public class User {
    private final UserId userId;

    public User(UserId UserId) {
        userId = UserId;
    }

    public boolean hasBorrowed(BookId bookId) {
        return true;
    }
}
