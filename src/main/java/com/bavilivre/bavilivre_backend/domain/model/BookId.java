package com.bavilivre.bavilivre_backend.domain.model;

import java.util.Objects;

public class BookId {


    private final int id;
    private final int ownerId;

    public int getId() {
        return id;
    }

    public BookId(int id, int ownerId) {
        this.id = id;
        this.ownerId = ownerId;
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

    public int getOwnerId() {
        return ownerId;
    }
}
