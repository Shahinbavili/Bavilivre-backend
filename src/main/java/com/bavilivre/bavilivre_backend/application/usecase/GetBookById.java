package com.bavilivre.bavilivre_backend.application.usecase;

import com.bavilivre.bavilivre_backend.application.port.BookRepository;
import com.bavilivre.bavilivre_backend.domain.exception.BookNotFoundException;
import com.bavilivre.bavilivre_backend.domain.model.book.Book;
import com.bavilivre.bavilivre_backend.domain.model.book.BookId;
import org.springframework.stereotype.Service;

@Service
public class GetBookById {

    private final BookRepository bookRepository;

    public GetBookById(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book handle(BookId bookId) {
        return bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException(bookId));
    }
}