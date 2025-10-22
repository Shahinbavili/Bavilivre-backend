package com.bavilivre.bavilivre_backend.domain.model;

import java.util.Objects;

public class BookId {
    private final int id;

    public BookId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "BookId{" +
                "id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        BookId bookId = (BookId) o;
        return id == bookId.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
