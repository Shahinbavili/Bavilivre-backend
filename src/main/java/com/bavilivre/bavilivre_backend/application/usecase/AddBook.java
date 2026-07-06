package com.bavilivre.bavilivre_backend.application.usecase;

import com.bavilivre.bavilivre_backend.application.port.BookRepository;
import com.bavilivre.bavilivre_backend.domain.model.book.Book;
import com.bavilivre.bavilivre_backend.domain.model.user.UserId;
import org.springframework.stereotype.Service;

@Service
public class AddBook {

    private final BookRepository bookRepository;

    public AddBook(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book handle(
            UserId ownerId,
            String title,
            String author,
            String description,
            String language,
            String category
    ) {
        Book book = new Book(
                null,
                ownerId,
                title,
                author,
                description,
                language,
                category,
                true,
                false
        );
        return bookRepository.save(book);

    }
}