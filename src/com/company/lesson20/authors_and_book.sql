CREATE SCHEMA library;

CREATE TABLE book
(
    book_id         SERIAL PRIMARY KEY,
    name            CHARACTER VARYING(64),
    number_of_pages INTEGER,
    genre           CHARACTER VARYING(64)
);

CREATE TABLE authors
(
    authors_id    SERIAL PRIMARY KEY,
    full_name     CHARACTER VARYING(64),
    date_of_birth DATE,
    nationality   CHARACTER VARYING(64)
);

INSERT INTO book (name, number_of_pages, genre)
VALUES ('War and Piece', 1300, 'novel'),
       ('Twelve chairs', 384, 'novel'),
       ('Golden calf', 608, 'novel'),
       ('It', 1138, 'novel'),
       ('Anna Karenina', 864, 'novel'),
       ('Saturday', 576, 'novel'),
       ('Green mile', 496, 'horror'),
       ('Carrie', 199, 'horror'),
       ('Hobbits', 252, 'novel'),
       ('Witcher', 640, 'novel');


INSERT INTO authors(full_name, date_of_birth, nationality)
VALUES ('Stiven King', '1947-09-21', 'american'),
       ('Lev Tolstoy', '1828-09-09', 'russian'),
       ('Petrov Evgeniy', '1902-09-09', 'russian'),
       ('Angey Sapkovski', '1948-06-21', 'polish'),
       ('John Tolkin', '1892-01-03', 'british'),
       ('Ilya Ilif', '1897-10-15', 'russian');


CREATE TABLE authors_to_book
(
    authors_id int REFERENCES authors ON DELETE CASCADE,
    book_id    int REFERENCES book ON DELETE CASCADE,
    PRIMARY KEY (book_id, authors_id)
);

INSERT INTO authors_to_book(authors_id, book_id)
VALUES (1, 4),
       (1, 6),
       (1, 7),
       (1, 8),
       (2, 1),
       (2, 5),
       (3, 2),
       (3, 3),
       (4, 10),
       (5, 9),
       (6, 2),
       (6, 3);

SELECT full_name AS book_author, name AS book_name
FROM authors
         JOIN authors_to_book ON authors.authors_id = authors_to_book.authors_id
         JOIN book ON book.book_id = authors_to_book.book_id;


SELECT name, number_of_pages, genre
FROM book
         JOIN authors_to_book atb on book.book_id = atb.book_id
         JOIN authors a on a.authors_id = atb.authors_id
WHERE nationality = 'russian';

SELECT name, number_of_pages, genre
FROM book
         JOIN authors_to_book atb on book.book_id = atb.book_id
         JOIN authors a on a.authors_id = atb.authors_id
WHERE EXTRACT(YEAR FROM date_of_birth) > 1900;

DELETE
FROM book
WHERE name = 'War and Piece';

SELECT full_name,
       nationality,
       avg_pages
FROM authors
         JOIN
     (
         SELECT authors.authors_id, AVG(number_of_pages) as avg_pages
         FROM authors as authors
                  JOIN authors_to_book atb on authors.authors_id = atb.authors_id
                  JOIN book b on b.book_id = atb.book_id
         GROUP BY authors.authors_id
     ) as avg ON authors.authors_id = avg.authors_id
ORDER BY date_of_birth;

SELECT full_name,
       nationality,
       AVG(number_of_pages) as average
FROM authors
         JOIN authors_to_book atb on authors.authors_id = atb.authors_id
         JOIN book b on b.book_id = atb.book_id
GROUP BY authors.authors_id;

SELECT full_name,
       nationality,
       avg_pages
FROM authors
         JOIN
     (
         SELECT authors.authors_id, AVG(number_of_pages) as avg_pages
         FROM authors as authors
                  JOIN authors_to_book atb on authors.authors_id = atb.authors_id
                  JOIN book b on b.book_id = atb.book_id
         GROUP BY authors.authors_id
     ) as avg ON authors.authors_id = avg.authors_id
WHERE avg_pages > 300
ORDER BY date_of_birth;