INSERT INTO users (id, display_name)
VALUES (1, 'Shahin');

INSERT INTO users (id, display_name)
VALUES (2, 'Alice');

INSERT INTO users (id, display_name)
VALUES (3, 'Bob');

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

INSERT INTO books (id,
                   owner_id,
                   title,
                   author,
                   description,
                   language,
                   category)
VALUES (2,
        1,
        'Domain-Driven Design',
        'Eric Evans',
        'A reference book about domain-driven design.',
        'en',
        'Software Architecture');

INSERT INTO books (id,
                   owner_id,
                   title,
                   author,
                   description,
                   language,
                   category)
VALUES (3,
        3,
        'Refactoring',
        'Martin Fowler',
        'Improving the design of existing code.',
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