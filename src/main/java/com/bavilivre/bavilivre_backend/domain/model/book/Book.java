package com.bavilivre.bavilivre_backend.domain.model.book;

import com.bavilivre.bavilivre_backend.domain.model.user.UserId;

public record Book(BookId id, UserId ownerId) {
}
