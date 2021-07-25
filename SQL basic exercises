-- create table books with primary key, title and genre
CREATE TABLE books (
    book_id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(71) NOT NULL,
    genre VARCHAR(20) DEFAULT('uknown')
);

-- insert into books table a few books
INSERT INTO books(title, genre) VALUES('Harry Potter', 'Fantasy');
INSERT INTO books(title, genre) VALUES('Witcher', 'Fantasy');
INSERT INTO books(title, genre) VALUES('Alien', 'Sci-Fi');
INSERT INTO books(title, genre) VALUES('World War', 'Historical');
INSERT INTO books(title, genre) VALUES('LOR', 'Fantasy');

-- show all books from books table
SELECT * FROM books;

-- show books id and title in descending id order
SELECT book_id,title FROM books 
WHERE genre = 'Fantasy' OR genre = 'Sci-Fi' ORDER BY book_id DESC;

-- change name of the Witcher book to Wiedźmin
UPDATE books
SET title = 'Wiedźmin'
WHERE title = 'Witcher';

-- add a column to books table called author with default name 'uknown'
ALTER TABLE books ADD author VARCHAR(40) DEFAULT('unkown');

-- update one book author in a table
UPDATE books
SET author = 'Andrzej Sapkowski'
WHERE book_id = 2;

-- create new table called clients with data: id, name, surname, borrow date, book id
CREATE TABLE clients (
    client_id INT PRIMARY KEY AUTO_INCREMENT,
    client_name VARCHAR(40) NOT NULL,
    client_surname VARCHAR(40) NOT NULL,
    borrow_date DATE,
    book_id INT,
    FOREIGN KEY(book_id) REFERENCES books(book_id) ON DELETE SET NULL 
);

-- insert 5 clients into clients table
INSERT INTO clients VALUES(1,'Erica', 'Love','2021-03-07', 1);
INSERT INTO clients VALUES(2,'Mateusz', 'Love','2021-03-07', 4);
INSERT INTO clients VALUES(3,'Dallo', 'Kimzi','2021-05-21', 2);
INSERT INTO clients VALUES(4,'Kazuha', 'Minori','2021-01-01', 1);
INSERT INTO clients VALUES(5,'Kirito', 'Kuro','2020-12-01', 3);

-- show all in clients table
SELECT * FROM clients;

-- check in popsql created table data types
DESCRIBE clients;

-- create table librarians with data: id, name, surname
CREATE TABLE librarians (
    librarianID INT PRIMARY KEY,
    librarianName VARCHAR(40),
    librarianSurname VARCHAR(40)
);

-- insert 3 librarians to librarians table
INSERT INTO librarians VALUES(1,'Yuko', 'Yuri');
INSERT INTO librarians VALUES(2,'Milo', 'Skuli');
INSERT INTO librarians VALUES(3,'Toast', 'Disguised');

-- show all librarians in the table
SELECT * FROM librarians;

-- add librarianID column as a foreign key to clients table
ALTER TABLE clients ADD librarianID INT, ADD 
FOREIGN KEY (librarianID) REFERENCES librarians(librarianID) ON DELETE SET NULL;

-- update clients librariansID to 1 with borrow date 2021-03-07 and to 2 with borrow date 2021-05-21 or 2020-12-01
UPDATE clients SET librarianID = 1 WHERE borrow_date = '2021-03-07';
UPDATE clients SET librarianID = 2 WHERE borrow_date = '2021-05-21' OR borrow_date = '2020-12-01';
-- insert new client containing a data about librarian id
INSERT INTO clients VALUES(6,'Vamoire', 'Loki','2021-07-09', 2, 3);

-- show books ordered by title
SELECT * FROM books ORDER BY title DESC;

-- show data from clients and librarians like name as forename, client surname as surname, librariansurname as librarian
SELECT client_name AS forename,client_surname AS surname, librarianSurname AS librarian FROM clients,librarians;

-- show distinct librarian id from clients table
SELECT DISTINCT librarianID FROM clients;

-- count all books borrowed by clients
SELECT COUNT(book_id) AS Book_Amount, book_id FROM clients
GROUP BY book_id;

-- count all clients
SELECT COUNT(client_name) FROM clients;

--create a wildcard selecting all with 03 month
SELECT * FROM clients WHERE borrow_date LIKE '____-03%';

--connect client table with librarian table and show data like: id and name in one columns
SELECT client_id AS ID,client_name AS Forename
FROM clients UNION
SELECT librarianID,librarianName
FROM librarians;

--show table with with clients id,name and book title which they borrowed using join query
SELECT clients.client_id, clients.client_name, books.title
FROM clients
JOIN books 
ON clients.book_id = books.book_id ORDER BY client_id;

--Nested queries show clients name and surname who got books with fantasy genre
SELECT clients.client_name, clients.client_surname FROM clients WHERE clients.book_id IN(
    SELECT books.book_id FROM books
    WHERE books.genre = 'Fantasy'
);

--Select librarians who did loan Sci-Fi books where you know book ID 3

SELECT librarians.librarianName , librarians.librarianSurname FROM librarians 
WHERE librarians.librarianID =(
    SELECT clients.librarianID FROM clients
    WHERE clients.book_id = 3
    LIMIT 1
);

--Creating trigger table with id and client name
CREATE TABLE trigger_insert(
    id INT,
    nameofclient VARCHAR(40)
);

--Setting insert trigger in mysql console
DELIMITER $$
CREATE
TRIGGER my_trigger BEFORE INSERT
ON clients
FOR EACH ROW BEGIN
INSERT INTO trigger_insert(id, nameofclient) VALUES(NEW.client_id, NEW.client_name);
END$$
DELIMITER ;

-- insert new librarian
INSERT INTO librarians VALUES(4,'Elizabeth', 'Re-Zero');

-- test of trigger by inserting new client
INSERT INTO clients (clients.client_id, clients.client_name, clients.client_surname, clients.borrow_date, clients.book_id, clients.librarianID) 
VALUES(7,'Vivy','Diva','2021.07.11',2,3);

-- example of deleting client
DELETE FROM clients WHERE client_id = 7;

-- show trigger table to check if trigger is added
SELECT * FROM trigger_insert;

-- for mysql triggers list in clients
SHOW TRIGGERS LIKE 'cl%'\G; 

-- insert new client with null book and librarian
INSERT INTO clients VALUES(8,'Kuma', 'Wakame','2021-07-25', NULL, NULL);

-- show all data from clients and all books with right join
SELECT * FROM clients
RIGHT JOIN books 
ON clients.book_id = books.book_id ORDER BY client_id;

-- show all data from clients and all books with left join
SELECT * FROM clients
LEFT JOIN books 
ON clients.book_id = books.book_id ORDER BY client_id;

-- nested queries find names of all clients who borrowed books from librarian between 1-2 ID
SELECT clients.client_name, clients.client_surname 
FROM clients
WHERE clients.librarianID IN 
(SELECT librarians.librarianID
FROM librarians
WHERE librarians.librarianID <= 2);

-- show list of books id borrowed more than once
SELECT clients.book_id, COUNT(clients.client_id)
FROM clients
GROUP BY clients.book_id
HAVING COUNT(clients.client_id) > 1
ORDER BY clients.book_id DESC;

-- show all books id and amount of clients who borrowed them without books not borrowed even once
SELECT clients.book_id, COUNT(clients.client_id)
FROM clients
WHERE clients.book_id IS NOT NULL
GROUP BY clients.book_id
ORDER BY clients.book_id DESC;

