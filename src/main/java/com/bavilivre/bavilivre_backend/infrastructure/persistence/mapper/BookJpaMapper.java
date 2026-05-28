package com.bavilivre.bavilivre_backend.infrastructure.persistence.mapper;

import com.bavilivre.bavilivre_backend.domain.model.book.Book;
import com.bavilivre.bavilivre_backend.domain.model.book.BookId;
import com.bavilivre.bavilivre_backend.domain.model.user.UserId;
import com.bavilivre.bavilivre_backend.infrastructure.persistence.entity.BookJpaEntity;
import org.springframework.stereotype.Component;

@Component
public class BookJpaMapper {

    private final UserJpaMapper userMapper = new UserJpaMapper();

    public Book toDomain(BookJpaEntity entity) {
        return new Book(
                new BookId(entity.getId()),
                new UserId(entity.getOwner().getId()),
                entity.getTitle(),
                entity.getAuthor(),
                entity.getDescription(),
                entity.getLanguage(),
                entity.getCategory()
        );
    }

    public BookJpaEntity toEntity(Book book) {
        return new BookJpaEntity(
                book.id().value(),
                userMapper.toEntity(book.ownerId()),
                book.title(),
                book.author(),
                book.description(),
                book.language(),
                book.category()
        );
    }
}