package com.bavilivre.bavilivre_backend.domain.model.book;

import com.bavilivre.bavilivre_backend.domain.model.user.UserId;

import java.time.LocalDateTime;

public record Book(
        BookId id,
        UserId ownerId,
        String title,
        String author,
        String description,
        String language,
        String category,
        boolean available,
        boolean archived,
        LocalDateTime createdAt
) {


    public Book markAsBorrowed() {
        return new Book(id, ownerId, title, author, description, language, category, false, archived, createdAt);
    }

    public Book markAsAvailable() {
        return new Book(id, ownerId, title, author, description, language, category, true, available, createdAt);
    }

    public Book archive() {
        return new Book(id, ownerId, title, author, description, language, category, available, true, createdAt);
    }

    public Book updateMetadata(
            String title,
            String author,
            String description,
            String language,
            String category
    ) {
        return new Book(id, ownerId, title, author, description, language, category, available, archived, createdAt);
    }

}
