INSERT INTO users (id, display_name)
VALUES (1, 'Shahin');

INSERT INTO users (id, display_name)
VALUES (2, 'Alice');

INSERT INTO users (id, display_name)
VALUES (3, 'Bob');

ALTER TABLE users
    ALTER COLUMN id RESTART WITH 4;

INSERT INTO books (id,
                   owner_id,
                   title,
                   author,
                   description,
                   language,
                   category,
                   available,
                   archived,
                   created_at)
VALUES (1,
        1,
        'Clean Code',
        'Robert C. Martin',
        'A practical guide to writing clean and maintainable code.',
        'en',
        'Software Engineering',
        true,
        false,
        TIMESTAMP '2026-07-01 10:00:00');

INSERT INTO books (id,
                   owner_id,
                   title,
                   author,
                   description,
                   language,
                   category,
                   available,
                   archived,
                   created_at)
VALUES (2,
        1,
        'Domain-Driven Design',
        'Eric Evans',
        'A reference book about domain-driven design.',
        'en',
        'Software Architecture',
        true,
        false,
        TIMESTAMP '2026-06-01 10:00:00');

INSERT INTO books (id,
                   owner_id,
                   title,
                   author,
                   description,
                   language,
                   category,
                   available,
                   archived,
                   created_at)
VALUES (3,
        3,
        'Refactoring',
        'Martin Fowler',
        'Improving the design of existing code.',
        'en',
        'Software Engineering',
        true,
        false,
        TIMESTAMP '2026-06-11 10:00:00');

ALTER TABLE books
    ALTER COLUMN id RESTART WITH 4;

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

INSERT INTO borrowings (
    id,
    book_id,
    borrower_id,
    lender_id,
    borrowed_at,
    returned_at
)
VALUES (
           2,
           3,
           1,
           3,
           CURRENT_DATE,
           NULL
       );

INSERT INTO user_accounts (user_id,
                           email,
                           password_hash)
VALUES (1,
        'shahinbavili@gmail.com',
        '$2a$10$iLrF2bg6gKPLag/3ZTpjve1ZUIGdDLivAy1Fpcm99jEElsvoJsZou');

INSERT INTO user_accounts (user_id,
                           email,
                           password_hash)
VALUES (2,
        'alice@bavilivre.dev',
        'password');

INSERT INTO user_accounts (user_id,
                           email,
                           password_hash)
VALUES (3,
        'bob@bavilivre.dev',
        'password');