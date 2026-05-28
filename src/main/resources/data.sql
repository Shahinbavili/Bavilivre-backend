INSERT INTO users (id)
VALUES (1);
INSERT INTO users (id)
VALUES (2);

INSERT INTO books (id,
                   owner_id,
                   title,
                   author,
                   description,
                   language,
                   category)
VALUES (1,
        1,
        'Clean Code',
        'Robert C. Martin',
        'A practical guide to writing clean and maintainable code.',
        'en',
        'Software Engineering');

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