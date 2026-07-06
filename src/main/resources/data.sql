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
                   archived)
VALUES (1,
        1,
        'Clean Code',
        'Robert C. Martin',
        'A practical guide to writing clean and maintainable code.',
        'en',
        'Software Engineering',
        true,
        false);

INSERT INTO books (id,
                   owner_id,
                   title,
                   author,
                   description,
                   language,
                   category,
                   available,
                   archived)
VALUES (2,
        1,
        'Domain-Driven Design',
        'Eric Evans',
        'A reference book about domain-driven design.',
        'en',
        'Software Architecture',
        true,
        false);

INSERT INTO books (id,
                   owner_id,
                   title,
                   author,
                   description,
                   language,
                   category,
                   available,
                   archived)
VALUES (3,
        3,
        'Refactoring',
        'Martin Fowler',
        'Improving the design of existing code.',
        'en',
        'Software Engineering',
        true,
        false);

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

INSERT INTO user_accounts (user_id,
                           email,
                           password_hash)
VALUES (1,
        'shahin@bavilivre.dev',
        'password');

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