package com.bavilivre.bavilivre_backend.infrastructure.persistence.adapter;

import com.bavilivre.bavilivre_backend.domain.model.book.BookId;
import com.bavilivre.bavilivre_backend.domain.model.borrowing.Borrowing;
import com.bavilivre.bavilivre_backend.domain.model.user.UserId;
import com.bavilivre.bavilivre_backend.infrastructure.persistence.entity.BookJpaEntity;
import com.bavilivre.bavilivre_backend.infrastructure.persistence.entity.UserJpaEntity;
import com.bavilivre.bavilivre_backend.infrastructure.persistence.mapper.BorrowingJpaMapper;
import com.bavilivre.bavilivre_backend.infrastructure.persistence.repository.BookSpringDataRepository;
import com.bavilivre.bavilivre_backend.infrastructure.persistence.repository.BorrowingSpringDataRepository;
import com.bavilivre.bavilivre_backend.infrastructure.persistence.repository.UserSpringDataRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@TestPropertySource(properties = {
        "spring.sql.init.mode=never"
})
class BorrowingJpaAdapterTest {

    private static final int BOOK_ID = 10;

    private static final LocalDate BORROWED_AT = LocalDate.of(2026, 5, 19);
    private static final LocalDate RETURNED_AT = LocalDate.of(2026, 5, 24);

    private Integer lenderId;
    private Integer borrowerId;

    @Autowired
    private BorrowingSpringDataRepository borrowingSpringDataRepository;

    @Autowired
    private UserSpringDataRepository userSpringDataRepository;

    @Autowired
    private BookSpringDataRepository bookSpringDataRepository;

    private BorrowingJpaAdapter adapter;

    @BeforeEach
    void setUp() {
        adapter = new BorrowingJpaAdapter(
                borrowingSpringDataRepository,
                new BorrowingJpaMapper()
        );

        saveUsersAndBook();

        borrowing = new Borrowing(
                null,
                new BookId(10),
                new UserId(borrowerId),
                new UserId(lenderId),
                BORROWED_AT
        );
    }


    private Borrowing borrowing;


    @Test
    void should_save_and_find_borrowing_by_borrower_and_lender() {

        adapter.save(borrowing);

        var borrowedByUser = adapter.findByBorrower_Id(new UserId(borrowerId));
        var lentByUser = adapter.findByLender_Id(new UserId(lenderId));

        assertThat(borrowedByUser).hasSize(1);

        assertThat(borrowedByUser.getFirst().bookId()).isEqualTo(new BookId(BOOK_ID));
        assertThat(borrowedByUser.getFirst().borrowerId()).isEqualTo(new UserId(borrowerId));

        assertThat(lentByUser).hasSize(1);

        assertThat(lentByUser.getFirst().lenderId()).isEqualTo(new UserId(lenderId));
    }

    @Test
    void should_store_returned_at_date() {

        borrowing.returnBook(RETURNED_AT);

        adapter.save(borrowing);

        var savedBorrowing = adapter.findByBorrower_Id(new UserId(borrowerId));

        assertThat(savedBorrowing).hasSize(1);
        assertThat(savedBorrowing.getFirst().returnedAt())
                .isEqualTo(RETURNED_AT);
        assertThat(savedBorrowing.getFirst().isReturned()).isTrue();
    }

    private void saveUsersAndBook() {
        UserJpaEntity lender = userSpringDataRepository.save(new UserJpaEntity(null, "Shahin"));
        UserJpaEntity borrower = userSpringDataRepository.save(new UserJpaEntity(null, "Alice"));

        lenderId = lender.getId();
        borrowerId = borrower.getId();

        bookSpringDataRepository.save(
                new BookJpaEntity(
                        10,
                        lender,
                        "Clean Code",
                        "Robert C. Martin",
                        "Clean code principles",
                        "en",
                        "Software Engineering",
                        true
                )
        );
    }
}
