package com.bavilivre.bavilivre_backend.domain.model.user;

import com.bavilivre.bavilivre_backend.domain.model.BookId;

import java.util.HashSet;
import java.util.Set;

public class User {
    private final UserId userId;
    Set<BookId> borrowedBooks;

    public User(UserId userId) {

        this.userId = userId;
        this.borrowedBooks = new HashSet<>();
    }

    public boolean hasBorrowed(BookId bookId) {
        return borrowedBooks.contains(bookId);
    }

    public void barrow(BookId bookId) {
        borrowedBooks.add(bookId);
    }

    public Set<BookId> borrowedBooks() {
        return borrowedBooks;
    }
}
