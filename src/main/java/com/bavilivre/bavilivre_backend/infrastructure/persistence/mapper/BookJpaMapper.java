package com.bavilivre.bavilivre_backend.infrastructure.persistence.mapper;

import com.bavilivre.bavilivre_backend.domain.model.book.Book;
import com.bavilivre.bavilivre_backend.domain.model.book.BookId;
import com.bavilivre.bavilivre_backend.domain.model.user.UserId;
import com.bavilivre.bavilivre_backend.infrastructure.persistence.entity.BookJpaEntity;
import com.bavilivre.bavilivre_backend.infrastructure.persistence.entity.UserJpaEntity;
import org.springframework.stereotype.Component;

@Component
public class BookJpaMapper {

    public Book toDomain(BookJpaEntity entity) {
        return new Book(
                new BookId(entity.getId()),
                new UserId(entity.getOwner().getId()),
                entity.getTitle(),
                entity.getAuthor(),
                entity.getDescription(),
                entity.getLanguage(),
                entity.getCategory(),
                entity.isAvailable(),
                entity.isArchived()
        );
    }

    public BookJpaEntity toEntity(Book book) {

        Integer entityId = book.id() == null ? null : book.id().value();

        return new BookJpaEntity(
                entityId,
                new UserJpaEntity(
                        book.ownerId().value(),
                        "Unknown"
                ),
                book.title(),
                book.author(),
                book.description(),
                book.language(),
                book.category(),
                book.available(),
                book.archived()
        );
    }
}