package com.bavilivre.bavilivre_backend.application.usecase;

import com.bavilivre.bavilivre_backend.application.port.BookRepository;
import com.bavilivre.bavilivre_backend.application.query.PageResult;
import com.bavilivre.bavilivre_backend.domain.model.book.Book;
import com.bavilivre.bavilivre_backend.domain.model.user.UserId;
import org.springframework.stereotype.Service;

@Service
public class GetMyBooks {

    private final BookRepository bookRepository;

    public GetMyBooks(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public PageResult<Book> handle(
            UserId currentUserId,
            int page,
            int size
    ) {
        return bookRepository.findByOwnerIdAndArchived(
                currentUserId,
                false,
                page,
                size
        );
    }
}