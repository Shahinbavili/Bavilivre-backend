package com.bavilivre.bavilivre_backend.application.usecase;

import com.bavilivre.bavilivre_backend.application.port.BookRepository;
import com.bavilivre.bavilivre_backend.domain.model.book.Book;
import com.bavilivre.bavilivre_backend.domain.model.user.UserId;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetArchivedBooks {

    private final BookRepository bookRepository;

    public GetArchivedBooks(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    //    only the logged-in user's archived books
    public List<Book> handle(UserId currentUserId) {
        return bookRepository.findByOwnerIdAndArchived(
                currentUserId,
                true
        );
    }
}