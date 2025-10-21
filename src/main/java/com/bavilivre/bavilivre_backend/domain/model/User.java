package com.bavilivre.bavilivre_backend.domain.model;

public class User {
    private final UserId userId;

    public User(UserId userId) {

        this.userId = userId;
    }

    public boolean hasBorrowed(BookId bookId) {
        return true;
    }

    public void barrow(BookId bookId) {
    }
}
