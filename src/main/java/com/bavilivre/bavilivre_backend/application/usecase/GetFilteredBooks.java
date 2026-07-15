package com.bavilivre.bavilivre_backend.application.usecase;

import com.bavilivre.bavilivre_backend.application.port.BookRepository;
import com.bavilivre.bavilivre_backend.application.query.BookFilter;
import com.bavilivre.bavilivre_backend.application.query.PageResult;
import com.bavilivre.bavilivre_backend.domain.model.book.Book;
import org.springframework.stereotype.Service;

@Service
public class GetFilteredBooks {
    private final BookRepository bookRepository;

    public GetFilteredBooks(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public PageResult<Book> handle(BookFilter filter) {
        return bookRepository.findByFilter(filter);
    }
}