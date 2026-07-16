package com.bavilivre.bavilivre_backend.infrastructure.persistence.mapper;

import com.bavilivre.bavilivre_backend.domain.model.book.Book;
import com.bavilivre.bavilivre_backend.infrastructure.controller.response.BookDto;
import org.springframework.stereotype.Component;

@Component
public class BookDtoMapper {

    public BookDto toDto(Book book) {
        return new BookDto(
                book.id().value(),
                book.ownerId().value(),
                book.title(),
                book.author(),
                book.description(),
                book.language(),
                book.category(),
                book.available(),
                book.archived(),
                book.createdAt()
        );
    }
}