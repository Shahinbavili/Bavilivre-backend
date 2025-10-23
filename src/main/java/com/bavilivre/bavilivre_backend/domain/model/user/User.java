package com.bavilivre.bavilivre_backend.domain.model.user;

import com.bavilivre.bavilivre_backend.domain.model.BookId;

import java.util.HashSet;
import java.util.Set;

public class User {
    private final UserId userId;
    private final Set<BookId> borrowedBooks;
    private Set<BookId> lentBooks;

    public User(UserId userId) {

        this.userId = userId;
        this.borrowedBooks = new HashSet<>();
    }

    public boolean hasBorrowed(BookId bookId) {
        return borrowedBooks.contains(bookId);
    }

    public void borrow(BookId bookId) {
        borrowedBooks.add(bookId);
    }

    public Set<BookId> borrowedBooks() {
        return borrowedBooks;
    }

    public Set<BookId> lentBooks() {
        return lentBooks;
    }
}
