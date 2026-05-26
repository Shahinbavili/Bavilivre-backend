INSERT INTO users (id)
VALUES (1);
INSERT INTO users (id)
VALUES (2);

INSERT INTO books (id, owner_id)
VALUES (1, 1);

INSERT INTO borrowings (id,
                        book_id,
                        borrower_id,
                        lender_id,
                        borrowed_at,
                        returned_at)
VALUES (1,
        1,
        2,
        1,
        CURRENT_DATE,
        NULL);