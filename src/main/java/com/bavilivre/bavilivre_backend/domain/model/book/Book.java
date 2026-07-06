package com.bavilivre.bavilivre_backend.domain.model.book;

import com.bavilivre.bavilivre_backend.domain.model.user.UserId;

public record Book(
        BookId id,
        UserId ownerId,
        String title,
        String author,
        String description,
        String language,
        String category,
        boolean available,
        boolean archived
) {


    public Book markAsBorrowed() {
        return new Book(id, ownerId, title, author, description, language, category, false, archived);
    }

    public Book markAsAvailable() {
        return new Book(id, ownerId, title, author, description, language, category, true, available);
    }

    public Book archive() {
        return new Book(id, ownerId, title, author, description, language, category, available, true);
    }

    public Book updateMetadata(
            String title,
            String author,
            String description,
            String language,
            String category
    ) {
        return new Book(id, ownerId, title, author, description, language, category, available, archived);
    }

}
