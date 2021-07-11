INSERT INTO library.address(AddressID, AddressLine1, City, State, PostalCode) VALUES (1, '123 Main Street', 'Philadelphia', 'PA', '19123');
INSERT INTO library.address(AddressID, AddressLine1, AddressLine2, City, State, PostalCode) VALUES (2, '54 Central Road', 'Apartment 3B', 'Philadelphia', 'PA', '19103');
INSERT INTO library.address(AddressID, AddressLine1, City, State, PostalCode) VALUES (3, '802 Suburban Terrace', 'Philadelphia', 'PA', '19123');
INSERT INTO library.address(AddressID, AddressLine1, City, State, PostalCode) VALUES (4, '37 Chester Road', 'Philadelphia', 'PA', '19123');
INSERT INTO library.address(AddressID, AddressLine1, City, State, PostalCode) VALUES (5, '11050 Liberty Place', 'Philadelphia', 'PA', '19103');
INSERT INTO library.address(AddressID, AddressLine1, City, State, PostalCode) VALUES (6, '1200 Library Street', 'Camden', 'NJ', '08105-1234');
INSERT INTO library.address(AddressID, AddressLine1, AddressLine2, City, State, PostalCode) VALUES (7, '11 Archers Crossing', 'Suite 1119', 'Camden', 'NJ', '08105');
INSERT INTO library.address(AddressID, AddressLine1, City, State, PostalCode) VALUES (8, '56 Taylor Road', 'Camden', 'NJ', '08103');

INSERT INTO library.library(LibraryID, Name, AddressID) VALUES (1, 'Greater Learning Library', 5);
INSERT INTO library.library(LibraryID, Name, AddressID) VALUES (2, 'New Day Library', 6);

INSERT INTO library.member(MemberID, FirstName, LastName, AddressID) VALUES (1, 'Steven', 'Douglass', 1);
INSERT INTO library.member(MemberID, FirstName, LastName, AddressID) VALUES (2, 'Thomas', 'Smith', 2);
INSERT INTO library.member(MemberID, FirstName, LastName, AddressID) VALUES (3, 'Diane', 'Mueller', 3);
INSERT INTO library.member(MemberID, FirstName, LastName, AddressID) VALUES (4, 'Margarette', 'Beasley', 4);
INSERT INTO library.member(MemberID, FirstName, LastName, AddressID) VALUES (5, 'Marcus', 'Brown', 7);
INSERT INTO library.member(MemberID, FirstName, LastName, AddressID) VALUES (6, 'Latoya', 'Bridges', 8);

INSERT INTO library.librarymember(LibraryMemberID, LibraryID, MemberID, MemberSince) VALUES (1, 1, 1, '2018-10-02');
INSERT INTO library.librarymember(LibraryMemberID, LibraryID, MemberID, MemberSince) VALUES (2, 2, 1, '2014-09-26');
INSERT INTO library.librarymember(LibraryMemberID, LibraryID, MemberID, MemberSince) VALUES (3, 2, 2, '2011-02-25');
INSERT INTO library.librarymember(LibraryMemberID, LibraryID, MemberID, MemberSince) VALUES (4, 1, 3, '2021-06-21');
INSERT INTO library.librarymember(LibraryMemberID, LibraryID, MemberID, MemberSince) VALUES (5, 2, 3, '2020-01-17');
INSERT INTO library.librarymember(LibraryMemberID, LibraryID, MemberID, MemberSince) VALUES (6, 1, 4, '2001-11-08');
INSERT INTO library.librarymember(LibraryMemberID, LibraryID, MemberID, MemberSince) VALUES (7, 1, 5, '2014-03-28');
INSERT INTO library.librarymember(LibraryMemberID, LibraryID, MemberID, MemberSince) VALUES (8, 2, 5, '2016-02-11');
INSERT INTO library.librarymember(LibraryMemberID, LibraryID, MemberID, MemberSince) VALUES (9, 1, 6, '2007-08-14');
INSERT INTO library.librarymember(LibraryMemberID, LibraryID, MemberID, MemberSince) VALUES (10, 2, 6, '2009-04-08');

INSERT INTO library.book(BookID, Title, PublishDate) VALUES (1, 'David Copperfield', '1849-05-01');
INSERT INTO library.book(BookID, Title, PublishDate) VALUES (2, 'Oliver Twist', '1838-01-01');
INSERT INTO library.book(BookID, Title, PublishDate) VALUES (3, 'A Tale of Two Cities', '1859-01-01');
INSERT INTO library.book(BookID, Title, PublishDate) VALUES (4, 'Adventures of Huckleberry Finn', '1884-12-10');
INSERT INTO library.book(BookID, Title, PublishDate) VALUES (5, 'The Adventures of Tom Sawyer', '1876-06-01');
INSERT INTO library.book(BookID, Title, PublishDate) VALUES (6, 'Harry Potter and the Sorcerers Stone', '1997-06-26');
INSERT INTO library.book(BookID, Title, PublishDate) VALUES (7, 'Harry Potter and the Deathly Hallows', '2007-07-21');
INSERT INTO library.book(BookID, Title, PublishDate) VALUES (8, 'Harry Potter and the Chamber of Secrets', '1998-07-02');
INSERT INTO library.book(BookID, Title, PublishDate) VALUES (9, 'A Farewell to Arms', '1929-01-01');
INSERT INTO library.book(BookID, Title, PublishDate) VALUES (10, 'The Old Man and the Sea', '1952-09-01');

INSERT INTO library.author(AuthorID, FirstName, LastName) VALUES (1, 'Ernest', 'Hemingway');
INSERT INTO library.author(AuthorID, FirstName, LastName) VALUES (2, 'Mark', 'Twain');
INSERT INTO library.author(AuthorID, FirstName, LastName) VALUES (3, 'J. K.', 'Rowling');
INSERT INTO library.author(AuthorID, FirstName, LastName) VALUES (4, 'Charles', 'Dickens');

INSERT INTO library.bookauthor(BookID, AuthorID) VALUES (1, 4);
INSERT INTO library.bookauthor(BookID, AuthorID) VALUES (2, 4);
INSERT INTO library.bookauthor(BookID, AuthorID) VALUES (3, 4);
INSERT INTO library.bookauthor(BookID, AuthorID) VALUES (4, 2);
INSERT INTO library.bookauthor(BookID, AuthorID) VALUES (5, 2);
INSERT INTO library.bookauthor(BookID, AuthorID) VALUES (6, 3);
INSERT INTO library.bookauthor(BookID, AuthorID) VALUES (7, 3);
INSERT INTO library.bookauthor(BookID, AuthorID) VALUES (8, 3);
INSERT INTO library.bookauthor(BookID, AuthorID) VALUES (9, 1);
INSERT INTO library.bookauthor(BookID, AuthorID) VALUES (10, 1);

INSERT INTO library.bookinstance(BookInstanceID, BookID, LibraryID) VALUES (1, 1, 1);
INSERT INTO library.bookinstance(BookInstanceID, BookID, LibraryID) VALUES (2, 1, 1);
INSERT INTO library.bookinstance(BookInstanceID, BookID, LibraryID) VALUES (3, 1, 2);
INSERT INTO library.bookinstance(BookInstanceID, BookID, LibraryID) VALUES (4, 1, 2);
INSERT INTO library.bookinstance(BookInstanceID, BookID, LibraryID) VALUES (5, 1, 2);
INSERT INTO library.bookinstance(BookInstanceID, BookID, LibraryID) VALUES (6, 2, 1);
INSERT INTO library.bookinstance(BookInstanceID, BookID, LibraryID) VALUES (7, 2, 1);
INSERT INTO library.bookinstance(BookInstanceID, BookID, LibraryID) VALUES (8, 2, 2);
INSERT INTO library.bookinstance(BookInstanceID, BookID, LibraryID) VALUES (9, 2, 2);
INSERT INTO library.bookinstance(BookInstanceID, BookID, LibraryID) VALUES (10, 2, 2);
INSERT INTO library.bookinstance(BookInstanceID, BookID, LibraryID) VALUES (11, 2, 2);
INSERT INTO library.bookinstance(BookInstanceID, BookID, LibraryID) VALUES (12, 3, 1);
INSERT INTO library.bookinstance(BookInstanceID, BookID, LibraryID) VALUES (13, 3, 2);
INSERT INTO library.bookinstance(BookInstanceID, BookID, LibraryID) VALUES (14, 4, 1);
INSERT INTO library.bookinstance(BookInstanceID, BookID, LibraryID) VALUES (15, 4, 2);
INSERT INTO library.bookinstance(BookInstanceID, BookID, LibraryID) VALUES (16, 4, 2);
INSERT INTO library.bookinstance(BookInstanceID, BookID, LibraryID) VALUES (17, 5, 1);
INSERT INTO library.bookinstance(BookInstanceID, BookID, LibraryID) VALUES (18, 5, 2);
INSERT INTO library.bookinstance(BookInstanceID, BookID, LibraryID) VALUES (19, 6, 1);
INSERT INTO library.bookinstance(BookInstanceID, BookID, LibraryID) VALUES (20, 6, 2);
INSERT INTO library.bookinstance(BookInstanceID, BookID, LibraryID) VALUES (21, 6, 2);
INSERT INTO library.bookinstance(BookInstanceID, BookID, LibraryID) VALUES (22, 7, 1);
INSERT INTO library.bookinstance(BookInstanceID, BookID, LibraryID) VALUES (23, 7, 2);
INSERT INTO library.bookinstance(BookInstanceID, BookID, LibraryID) VALUES (24, 8, 1);
INSERT INTO library.bookinstance(BookInstanceID, BookID, LibraryID) VALUES (25, 8, 2);
INSERT INTO library.bookinstance(BookInstanceID, BookID, LibraryID) VALUES (26, 9, 1);
INSERT INTO library.bookinstance(BookInstanceID, BookID, LibraryID) VALUES (27, 9, 1);
INSERT INTO library.bookinstance(BookInstanceID, BookID, LibraryID) VALUES (28, 9, 2);
INSERT INTO library.bookinstance(BookInstanceID, BookID, LibraryID) VALUES (29, 10, 1);
INSERT INTO library.bookinstance(BookInstanceID, BookID, LibraryID) VALUES (30, 10, 2);

INSERT INTO library.checkouttransaction(CheckOutTransactionID, BookInstanceID, MemberID, DateCheckedOut) VALUES (1, 22, 1, '2020-02-06');
INSERT INTO library.checkouttransaction(CheckOutTransactionID, BookInstanceID, MemberID, DateCheckedOut) VALUES (2, 30, 5, '2020-02-22');
INSERT INTO library.checkouttransaction(CheckOutTransactionID, BookInstanceID, MemberID, DateCheckedOut) VALUES (3, 11, 1, '2020-03-16');
INSERT INTO library.checkouttransaction(CheckOutTransactionID, BookInstanceID, MemberID, DateCheckedOut) VALUES (4, 03, 1, '2020-04-22');
INSERT INTO library.checkouttransaction(CheckOutTransactionID, BookInstanceID, MemberID, DateCheckedOut) VALUES (5, 06, 3, '2020-04-27');
INSERT INTO library.checkouttransaction(CheckOutTransactionID, BookInstanceID, MemberID, DateCheckedOut) VALUES (6, 24, 6, '2020-05-02');
INSERT INTO library.checkouttransaction(CheckOutTransactionID, BookInstanceID, MemberID, DateCheckedOut) VALUES (7, 17, 6, '2020-05-02');
INSERT INTO library.checkouttransaction(CheckOutTransactionID, BookInstanceID, MemberID, DateCheckedOut) VALUES (8, 10, 3, '2020-04-22');

INSERT INTO library.checkintransaction(CheckInTransactionID, CheckOutTransactionID, DateCheckedIn) VALUES (1, 1, '2020-03-16');
INSERT INTO library.checkintransaction(CheckInTransactionID, CheckOutTransactionID, DateCheckedIn) VALUES (2, 3, '2020-04-22');
INSERT INTO library.checkintransaction(CheckInTransactionID, CheckOutTransactionID, DateCheckedIn) VALUES (3, 2, '2020-05-01');
INSERT INTO library.checkintransaction(CheckInTransactionID, CheckOutTransactionID, DateCheckedIn) VALUES (4, 5, '2020-05-11');