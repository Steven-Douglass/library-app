DROP SCHEMA IF EXISTS library;
CREATE SCHEMA IF NOT EXISTS library DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE library;

DROP TABLE IF EXISTS library.checkintransaction;
DROP TABLE IF EXISTS library.checkouttransaction;
DROP TABLE IF EXISTS library.bookinstance;
DROP TABLE IF EXISTS library.bookauthor;
DROP TABLE IF EXISTS library.author;
DROP TABLE IF EXISTS library.book;
DROP TABLE IF EXISTS library.librarymember;
DROP TABLE IF EXISTS library.member;
DROP TABLE IF EXISTS library.library;
DROP TABLE IF EXISTS library.address;

-- -----------------------------------------------------
-- Table library.address
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS library.address (
    AddressID integer NOT NULL AUTO_INCREMENT,
    AddressLine1 VARCHAR(45) NOT NULL,
    AddressLine2 VARCHAR(45) NULL DEFAULT NULL,
    City VARCHAR(45) NOT NULL,
    State VARCHAR(2) NOT NULL,
    PostalCode VARCHAR(10) NOT NULL,
    PRIMARY KEY (AddressID))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

CREATE UNIQUE INDEX AddressID_UNIQUE ON library.address (AddressID ASC) VISIBLE;

-- -----------------------------------------------------
-- Table library.library
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS library.library (
    LibraryID integer NOT NULL AUTO_INCREMENT,
    Name VARCHAR(45) NOT NULL,
    AddressID integer NOT NULL,
    PRIMARY KEY (LibraryID),
    constraint fk_library_address
    FOREIGN KEY (AddressID)
    REFERENCES library.address (AddressID))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

CREATE UNIQUE INDEX LibraryID_UNIQUE ON library.library (LibraryID ASC) VISIBLE;
CREATE INDEX AddressID_idx ON library.library (AddressID ASC) VISIBLE;

-- -----------------------------------------------------
-- Table library.member
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS library.member (
    MemberID integer NOT NULL AUTO_INCREMENT,
    FirstName VARCHAR(45) NOT NULL,
    LastName VARCHAR(45) NOT NULL,
    AddressID integer NOT NULL,
    PRIMARY KEY (MemberID),
    constraint fk_member_address
    FOREIGN KEY (AddressID)
    REFERENCES library.address (AddressID))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

CREATE UNIQUE INDEX MemberID_UNIQUE ON library.member (MemberID ASC) VISIBLE;
CREATE INDEX fk_member_address_idx ON library.member (AddressID ASC) VISIBLE;

-- -----------------------------------------------------
-- Table library.librarymember
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS library.librarymember (
    LibraryMemberID integer NOT NULL AUTO_INCREMENT,
    LibraryID integer NOT NULL,
    MemberID integer NOT NULL,
    MemberSince date NOT NULL,
    PRIMARY KEY (LibraryMemberID),
    constraint fk_librarymember_library
    FOREIGN KEY (LibraryID)
    REFERENCES library.library (LibraryID),
    constraint fk_librarymember_member
    FOREIGN KEY (MemberID)
    REFERENCES library.member (MemberID))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

CREATE UNIQUE INDEX LibraryMemberID_UNIQUE ON library.librarymember (LibraryMemberID ASC) VISIBLE;
CREATE INDEX fk_librarymember_library_idx ON library.librarymember (LibraryID ASC) VISIBLE;
CREATE INDEX fk_librarymember_member_idx ON library.librarymember (MemberID ASC) VISIBLE;

-- -----------------------------------------------------
-- Table library.book
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS library.book (
    BookID integer NOT NULL AUTO_INCREMENT,
    Title VARCHAR(100) NOT NULL,
    PublishDate date NULL DEFAULT NULL,
    PRIMARY KEY (BookID))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

CREATE UNIQUE INDEX BookID_UNIQUE ON library.book (BookID ASC) VISIBLE;

-- -----------------------------------------------------
-- Table library.author
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS library.author (
    AuthorID integer NOT NULL AUTO_INCREMENT,
    FirstName VARCHAR(45) NOT NULL,
    LastName VARCHAR(45) NULL DEFAULT NULL,
    PRIMARY KEY (AuthorID))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

CREATE UNIQUE INDEX AuthorID_UNIQUE ON library.author (AuthorID ASC) VISIBLE;

-- -----------------------------------------------------
-- Table library.bookauthor
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS library.bookauthor (
    BookAuthorID integer NOT NULL AUTO_INCREMENT,
    BookID integer NOT NULL,
    AuthorID integer NOT NULL,
    PRIMARY KEY (BookAuthorID),
    constraint fk_bookauthor_author
    FOREIGN KEY (AuthorID)
    REFERENCES library.author (AuthorID),
    constraint fk_bookauthor_book
    FOREIGN KEY (BookID)
    REFERENCES library.book (BookID))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

CREATE UNIQUE INDEX BookAuthorID_UNIQUE ON library.bookauthor (BookAuthorID ASC) VISIBLE;
CREATE INDEX fk_bookauthor_book_idx ON library.bookauthor (BookID ASC) VISIBLE;
CREATE INDEX fk_bookauthor_author_idx ON library.bookauthor (AuthorID ASC) VISIBLE;

-- -----------------------------------------------------
-- Table library.bookinstance
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS library.bookinstance (
    BookInstanceID integer NOT NULL AUTO_INCREMENT,
    BookID integer NOT NULL,
    LibraryID integer NOT NULL,
    PRIMARY KEY (BookInstanceID),
    constraint fk_bookinstance_book
    FOREIGN KEY (BookID)
    REFERENCES library.book (BookID),
    constraint fk_bookinstance_library
    FOREIGN KEY (LibraryID)
    REFERENCES library.library (LibraryID))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

CREATE UNIQUE INDEX BookInstanceID_UNIQUE ON library.bookinstance (BookInstanceID ASC) VISIBLE;
CREATE INDEX fk_bookinstance_library_idx ON library.bookinstance (LibraryID ASC) VISIBLE;
CREATE INDEX fk_bookinstance_book_idx ON library.bookinstance (BookID ASC) VISIBLE;

-- -----------------------------------------------------
-- Table library.checkouttransaction
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS library.checkouttransaction (
    CheckOutTransactionID integer NOT NULL AUTO_INCREMENT,
    BookInstanceID integer NOT NULL,
    MemberID integer NOT NULL,
    DateCheckedOut date NOT NULL,
    PRIMARY KEY (CheckOutTransactionID),
    constraint fk_checkouttransaction_bookinstance
    FOREIGN KEY (BookInstanceID)
    REFERENCES library.bookinstance (BookInstanceID),
    constraint fk_checkouttransaction_member
    FOREIGN KEY (MemberID)
    REFERENCES library.member (MemberID))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

CREATE UNIQUE INDEX CheckOutTransactionID_UNIQUE ON library.checkouttransaction (CheckOutTransactionID ASC) VISIBLE;
CREATE INDEX fk_checkouttransaction_bookinstance_idx ON library.checkouttransaction (BookInstanceID ASC) VISIBLE;
CREATE INDEX fk_checkouttransaction_member_idx ON library.checkouttransaction (MemberID ASC) VISIBLE;

-- -----------------------------------------------------
-- Table library.checkintransaction
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS library.checkintransaction (
    CheckInTransactionID integer NOT NULL AUTO_INCREMENT,
    CheckOutTransactionID integer NOT NULL,
    DateCheckedIn date NOT NULL,
    PRIMARY KEY (CheckInTransactionID),
    constraint fk_checkintransaction_checkouttransaction
    FOREIGN KEY (CheckOutTransactionID)
    REFERENCES library.checkouttransaction (CheckOutTransactionID))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

CREATE UNIQUE INDEX CheckInTransactionID_UNIQUE ON library.checkintransaction (CheckInTransactionID ASC) VISIBLE;
CREATE INDEX fk_checkintransaction_checkouttransaction_idx ON library.checkintransaction (CheckOutTransactionID ASC) VISIBLE;