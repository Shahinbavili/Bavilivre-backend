package com.bavilivre.bavilivre_backend.domain.application;

import java.util.Set;

public class BorrowDto {
    Set<Integer> borrowedBookIds;

    public Set<Integer> getBorrowedBookList() {
        return borrowedBookIds;
    }

    public void setBorrowedBookList(Set<Integer> borrowedBookList) {
        this.borrowedBookIds = borrowedBookList;
    }
}
