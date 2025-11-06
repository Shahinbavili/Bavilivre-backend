package com.bavilivre.bavilivre_backend.domain.application;

import java.util.Map;
import java.util.Set;

public class BorrowDto {
    Map<Integer, Integer> borrowedBookIds;

    public Map<Integer, Integer> getBorrowedBookList() {
        return borrowedBookIds;
    }

    public void setBorrowedBookList(Map<Integer, Integer> borrowedBookList) {
        this.borrowedBookIds = borrowedBookList;
    }
}
