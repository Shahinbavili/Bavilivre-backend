package com.bavilivre.bavilivre_backend.application.usecase;

import com.bavilivre.bavilivre_backend.application.port.BookRepository;
import com.bavilivre.bavilivre_backend.domain.exception.BookNotFoundException;
import com.bavilivre.bavilivre_backend.domain.exception.UserIsNotBookOwnerException;
import com.bavilivre.bavilivre_backend.domain.model.book.Book;
import com.bavilivre.bavilivre_backend.domain.model.book.BookId;
import com.bavilivre.bavilivre_backend.domain.model.user.UserId;
import org.springframework.stereotype.Service;

@Service
public class ArchiveBook {

    private final BookRepository bookRepository;

    public ArchiveBook(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book handle(
            BookId bookId,
            UserId currentUserId
    ) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException(bookId));

        if (!book.ownerId().equals(currentUserId)) {
            throw new UserIsNotBookOwnerException(currentUserId, bookId);
        }

        Book archivedBook = book.archive();

        return bookRepository.save(archivedBook);
    }
}