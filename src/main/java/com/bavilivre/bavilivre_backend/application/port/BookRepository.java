package com.bavilivre.bavilivre_backend.application.port;

import com.bavilivre.bavilivre_backend.domain.model.book.Book;
import com.bavilivre.bavilivre_backend.domain.model.book.BookId;

import java.util.List;
import java.util.Optional;

public interface BookRepository {

    Optional<Book> findById(BookId bookId);

    List<Book> findAll();

    List<Book> search(String query);

    Book save(Book book);
}