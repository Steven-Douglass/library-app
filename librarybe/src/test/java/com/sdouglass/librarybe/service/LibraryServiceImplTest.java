package com.sdouglass.librarybe.service;

import com.sdouglass.librarybe.entity.*;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@NoArgsConstructor
@TestPropertySource(
        locations = "classpath:application-integrationtest.properties")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Sql(scripts = {"/init.sql",  "/populateDB.sql"},
    config = @SqlConfig(transactionMode = SqlConfig.TransactionMode.ISOLATED))
public class LibraryServiceImplTest {
    @Autowired
    private LibraryService libraryService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @Order(1)
    void getAddress() {
        // Given
        Address expectedAddress = new Address();
        expectedAddress.setAddressID(6);
        expectedAddress.setAddressLine1("1200 Library Street");
        expectedAddress.setCity("Camden");
        expectedAddress.setState("NJ");
        expectedAddress.setPostalCode("08105-1234");

        // When
        Address actualAddress = libraryService.getAddress(6);

        // Then
        assertTrue(expectedAddress.equals(actualAddress));
    }

    @Test
    @Order(2)
    void getAllAddresses() {
        // When
        List<Address> addresses = libraryService.getAllAddresses();

        // Then
        assertEquals(8, addresses.size());
    }

    @Test
    @Order(3)
    void saveAddress() {
        // Given
        Address newAddress = new Address();
        newAddress.setAddressLine1("84 Huffington Lane");
        newAddress.setAddressLine2("Apt H6");
        newAddress.setCity("Cherry Hill");
        newAddress.setState("NJ");
        newAddress.setPostalCode("08002");

        // When
        libraryService.saveAddress(newAddress);
        Address savedAddress = libraryService.getAddress(9);

        // Then
        assertTrue(newAddress.equals(savedAddress));
    }

    @Test
    @Order(4)
    void deleteAddress() {
        // Given
        String expectedException = "Address ID not found - 2";
        String actualException = "";

        // When
        libraryService.deleteAddress(2);
        try {
            Address address = libraryService.getAddress(2);
        } catch (RuntimeException e) {
            actualException = e.getMessage();
        }

        // Then
        assertEquals(expectedException, actualException);
    }

    @Test
    @Order(5)
    void getAuthor() {
        // Given
        Author expectedAuthor = new Author();
        expectedAuthor.setAuthorID(4);
        expectedAuthor.setFirstName("Charles");
        expectedAuthor.setLastName("Dickens");

        // When
        Author actualAuthor = libraryService.getAuthor(4);

        // Then
        assertTrue(expectedAuthor.equals(actualAuthor));
    }

    @Test
    @Order(6)
    void getAllAuthors() {
        // When
        List<Author> authors = libraryService.getAllAuthors();

        // Then
        assertEquals(4, authors.size());
    }

    @Test
    @Order(7)
    void saveAuthor() {
        // Given
        Author newAuthor = new Author();
        newAuthor.setFirstName("Maya");
        newAuthor.setLastName("Angelou");

        // When
        libraryService.saveAuthor(newAuthor);
        Author savedAuthor = libraryService.getAuthor(5);

        // Then
        assertTrue(newAuthor.equals(savedAuthor));
    }

    @Test
    @Order(8)
    void deleteAuthor() {
        // Given
        String expectedException = "Author ID not found - 1";
        String actualException = "";

        // When
        libraryService.deleteAuthor(1);
        try {
            Author author = libraryService.getAuthor(1);
        } catch (RuntimeException e) {
            actualException = e.getMessage();
        }

        // Then
        assertEquals(expectedException, actualException);
    }

    @Test
    @Order(9)
    void getBook() {
        // Given
        Book expectedBook = new Book();
        expectedBook.setBookID(1);
        expectedBook.setTitle("David Copperfield");
        expectedBook.setPublishDate("1849-05-01");

        // When
        Book actualBook = libraryService.getBook(1);

        // Then
        assertTrue(expectedBook.equals(actualBook));
    }

    @Test
    @Order(10)
    void getAllBooks() {
        // When
        List<Book> books = libraryService.getAllBooks();

        // Then
        assertEquals(10, books.size());
    }

    @Test
    @Order(11)
    void saveBook() {
        // Given
        Book newBook = new Book();
        newBook.setTitle("Roughing It");
        newBook.setPublishDate("1872-02-01");

        // When
        libraryService.saveBook(newBook);
        Book savedBook = libraryService.getBook(11);

        // Then
        assertTrue(newBook.equals(savedBook));
    }

    @Test
    @Order(12)
    void deleteBook() {
        // Given
        String expectedException = "Book ID not found - 1";
        String actualException = "";

        // When
        libraryService.deleteBook(1);
        try {
            Book book = libraryService.getBook(1);
        } catch (RuntimeException e) {
            actualException = e.getMessage();
        }

        // Then
        assertEquals(expectedException, actualException);
    }

    @Test
    @Order(13)
    void getLibrary() {
        // Given
        Library expectedLibrary = new Library();
        expectedLibrary.setLibraryID(1);
        expectedLibrary.setName("Greater Learning Library");
        expectedLibrary.setAddressID(5);

        // When
        Library actualLibrary = libraryService.getLibrary(1);

        // Then
        assertTrue(expectedLibrary.equals(actualLibrary));
    }

    @Test
    @Order(14)
    void getAllLibraries() {
        // When
        List<Library> libraries = libraryService.getAllLibraries();

        // Then
        assertEquals(2, libraries.size());
    }

    @Test
    @Order(15)
    void saveLibrary() {
        // Given
        Address newAddress = new Address();
        newAddress.setAddressLine1("414 Burberry Plaza");
        newAddress.setAddressLine2("Unit 37");
        newAddress.setCity("Philadelphia");
        newAddress.setState("PA");
        newAddress.setPostalCode("19103");
        libraryService.saveAddress(newAddress);

        Library newLibrary = new Library();
        newLibrary.setName("The Free Library Of Philadelphia");
        newLibrary.setAddressID(9);

        // When
        libraryService.saveLibrary(newLibrary);
        Library savedLibrary = libraryService.getLibrary(3);

        // Then
        assertTrue(newLibrary.equals(savedLibrary));
    }

    @Test
    @Order(16)
    void deleteLibrary() {
        // Given
        String expectedException = "Library ID not found - 1";
        String actualException = "";

        // When
        libraryService.deleteLibrary(1);
        try {
            Library library = libraryService.getLibrary(1);
        } catch (RuntimeException e) {
            actualException = e.getMessage();
        }

        // Then
        assertEquals(expectedException, actualException);
    }

    @Test
    @Order(17)
    void getMember() {
        // Given
        Member expectedMember = new Member();
        expectedMember.setMemberID(1);
        expectedMember.setFirstName("Steven");
        expectedMember.setLastName("Douglass");
        expectedMember.setAddressID(1);

        // When
        Member actualMember = libraryService.getMember(1);

        // Then
        assertTrue(expectedMember.equals(actualMember));
    }

    @Test
    @Order(18)
    void getAllMembers() {
        // When
        List<Member> members = libraryService.getAllMembers();

        // Then
        assertEquals(6, members.size());
    }

    @Test
    @Order(19)
    void saveMember() {
        // Given
        Address newAddress = new Address();
        newAddress.setAddressLine1("414 Burberry Plaza");
        newAddress.setAddressLine2("Unit 37");
        newAddress.setCity("Philadelphia");
        newAddress.setState("PA");
        newAddress.setPostalCode("19103");
        libraryService.saveAddress(newAddress);

        Member newMember = new Member();
        newMember.setFirstName("Jon");
        newMember.setLastName("Tran");
        newMember.setAddressID(9);

        // When
        libraryService.saveMember(newMember);
        Member savedMember = libraryService.getMember(7);

        // Then
        assertTrue(newMember.equals(savedMember));
    }

    @Test
    @Order(20)
    void deleteMember() {
        // Given
        String expectedException = "Member ID not found - 1";
        String actualException = "";

        // When
        try {
            libraryService.deleteMember(1);
            Member member = libraryService.getMember(1);
        } catch (DataIntegrityViolationException e) {
            actualException = e.getMessage();
        } catch (RuntimeException e) {
            actualException = e.getMessage();
        }

        // Then
        assertEquals(expectedException, actualException);
    }
}