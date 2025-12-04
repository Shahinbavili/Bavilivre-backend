package com.bavilivre.bavilivre_backend.domain.model.user;

public record UserId(int id) {
    public int value() {
        return this.id;
    }
}
