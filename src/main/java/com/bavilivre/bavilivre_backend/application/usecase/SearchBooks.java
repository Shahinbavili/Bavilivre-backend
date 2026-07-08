package com.bavilivre.bavilivre_backend.application.usecase;

import com.bavilivre.bavilivre_backend.application.port.BookRepository;
import com.bavilivre.bavilivre_backend.domain.model.book.Book;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchBooks {

    private final BookRepository bookRepository;

    public SearchBooks(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> hanle(String query) {
        return bookRepository.search(query);
    }
}