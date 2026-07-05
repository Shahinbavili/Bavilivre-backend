package com.bavilivre.bavilivre_backend.application.usecase;

import com.bavilivre.bavilivre_backend.application.port.BookRepository;
import com.bavilivre.bavilivre_backend.domain.exception.BookNotFoundException;
import com.bavilivre.bavilivre_backend.domain.exception.UserIsNotBookOwnerException;
import com.bavilivre.bavilivre_backend.domain.model.book.Book;
import com.bavilivre.bavilivre_backend.domain.model.book.BookId;
import com.bavilivre.bavilivre_backend.domain.model.user.UserId;
import org.springframework.stereotype.Service;

@Service
public class UpdateBook {

    private final BookRepository bookRepository;

    public UpdateBook(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book handle(
            BookId bookId,
            UserId currentUserId,
            String title,
            String author,
            String description,
            String language,
            String category
    ) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException(bookId));

//        Owner validation
        if (!book.ownerId().equals(currentUserId)) {
            throw new UserIsNotBookOwnerException(currentUserId, bookId);
        }

//        Update the book
        Book updatedBook = book.updateMetadata(title, author, description, language, category);

        return bookRepository.save(updatedBook);
    }
}