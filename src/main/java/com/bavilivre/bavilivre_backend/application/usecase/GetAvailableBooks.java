package com.bavilivre.bavilivre_backend.application.usecase;

import com.bavilivre.bavilivre_backend.application.port.BookRepository;
import com.bavilivre.bavilivre_backend.domain.model.book.Book;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAvailableBooks {

    private final BookRepository bookRepository;

    public GetAvailableBooks(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> handle() {
        return bookRepository.findAll();
    }
}