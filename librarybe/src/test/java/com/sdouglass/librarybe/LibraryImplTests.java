package com.sdouglass.librarybe;

import com.sdouglass.librarybe.address.entity.Address;
import com.sdouglass.librarybe.address.service.AddressService;
import com.sdouglass.librarybe.author.entity.Author;
import com.sdouglass.librarybe.author.service.AuthorService;
import com.sdouglass.librarybe.checkouttransaction.entity.CheckOutTransaction;
import com.sdouglass.librarybe.checkouttransaction.service.CheckOutTransactionService;
import com.sdouglass.librarybe.entity.*;
import com.sdouglass.librarybe.service.LibraryService;
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
public class LibraryImplTests {
    @Autowired
    private LibraryService libraryService;
    @Autowired
    private AddressService addressService;
    @Autowired
    private AuthorService authorService;
    @Autowired
    private CheckOutTransactionService checkOutTransactionService;

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
        Address actualAddress = addressService.getAddress(6);

        // Then
        assertTrue(expectedAddress.equals(actualAddress));
    }

    @Test
    @Order(2)
    void getAllAddresses() {
        // When
        List<Address> addresses = addressService.getAllAddresses();

        // Then
        assertEquals(8, addresses.size());
    }

    @Test
    @Order(3)
    void saveNewAddress() {
        // Given
        Address newAddress = new Address();
        newAddress.setAddressLine1("84 Huffington Lane");
        newAddress.setAddressLine2("Apt H6");
        newAddress.setCity("Cherry Hill");
        newAddress.setState("NJ");
        newAddress.setPostalCode("08002");

        // When
        addressService.saveAddress(newAddress);
        Address savedAddress = addressService.getAddress(9);

        // Then
        assertTrue(newAddress.equals(savedAddress));
    }

    @Test
    @Order(4)
    void updateExistingAddress() {
        // Given
        Address existingAddress = addressService.getAddress(1);
        existingAddress.setAddressLine1("941 Hummingbird Lane");
        existingAddress.setAddressLine2("Apt 16B");
        existingAddress.setCity("Cherry Hill");
        existingAddress.setState("NJ");
        existingAddress.setPostalCode("08108");

        // When
        addressService.saveAddress(existingAddress);
        Address savedAddress = addressService.getAddress(1);

        // Then
        assertTrue(existingAddress.equals(savedAddress));
    }

    @Test
    @Order(5)
    void deleteAddress() {
        // Given
        String expectedException = "Address ID not found - 9";
        String actualException = "";

        Address newAddress = new Address();
        newAddress.setAddressLine1("84 Huffington Lane");
        newAddress.setAddressLine2("Apt H6");
        newAddress.setCity("Cherry Hill");
        newAddress.setState("NJ");
        newAddress.setPostalCode("08002");

        // When
        addressService.saveAddress(newAddress);
        Address savedAddress = addressService.getAddress(9);
        addressService.deleteAddress(savedAddress.getAddressID());
        try {
            Address address = addressService.getAddress(9);
        } catch (RuntimeException e) {
            actualException = e.getMessage();
        }

        // Then
        assertEquals(expectedException, actualException);
    }

    @Test
    @Order(6)
    void getAllAuthors() {
        // When
        List<Author> authors = authorService.getAllAuthors();

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
        authorService.saveAuthor(newAuthor);
        Author savedAuthor = authorService.getAuthor(5);

        // Then
        assertTrue(newAuthor.equals(savedAuthor));
    }

    @Test
    @Order(8)
    void deleteAuthor() {
        // Given
        String expectedException = "Author ID not found - 5";
        String actualException = "";

        Author newAuthor = new Author();
        newAuthor.setFirstName("Maya");
        newAuthor.setLastName("Angelou");

        // When
        authorService.saveAuthor(newAuthor);
        Author savedAuthor = authorService.getAuthor(5);

        authorService.deleteAuthor(5);
        try {
            Author author = authorService.getAuthor(5);
        } catch (RuntimeException e) {
            actualException = e.getMessage();
        }

        // Then
        assertTrue(newAuthor.equals(savedAuthor));
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
        String expectedException = "Book ID not found - 11";
        String actualException = "";
        Book newBook = new Book();
        newBook.setTitle("Roughing It");
        newBook.setPublishDate("1872-02-01");

        // When
        libraryService.saveBook(newBook);
        Book savedBook = libraryService.getBook(11);

        libraryService.deleteBook(11);
        try {
            Book book = libraryService.getBook(11);
        } catch (RuntimeException e) {
            actualException = e.getMessage();
        }

        // Then
        assertEquals(newBook, savedBook);
        assertEquals(expectedException, actualException);
    }

    @Test
    @Order(13)
    void getLibrary() {
        // Given
        Library expectedLibrary = new Library();
        expectedLibrary.setLibraryID(1);
        expectedLibrary.setName("Greater Learning Library");

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

        Library newLibrary = new Library();
        newLibrary.setName("The Free Library Of Philadelphia");
        newLibrary.setAddress(newAddress);

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
        String expectedException = "Library ID not found - 3";
        String actualException = "";

        Address newAddress = new Address();
        newAddress.setAddressLine1("414 Burberry Plaza");
        newAddress.setAddressLine2("Unit 37");
        newAddress.setCity("Philadelphia");
        newAddress.setState("PA");
        newAddress.setPostalCode("19103");

        Library newLibrary = new Library();
        newLibrary.setName("The Free Library Of Philadelphia");
        newLibrary.setAddress(newAddress);
        libraryService.saveLibrary(newLibrary);

        // When
        Library savedLibrary = libraryService.getLibrary(3);
        libraryService.deleteLibrary(3);
        try {
            Library library = libraryService.getLibrary(3);
        } catch (RuntimeException e) {
            actualException = e.getMessage();
        }

        // Then
        assertEquals(newLibrary, savedLibrary);
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

        Member newMember = new Member();
        newMember.setFirstName("Jon");
        newMember.setLastName("Tran");
        newMember.setAddress(newAddress);

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
        String expectedException = "Member ID not found - 6";
        String actualException = "";

        Address newAddress = new Address();
        newAddress.setAddressLine1("414 Burberry Plaza");
        newAddress.setAddressLine2("Unit 37");
        newAddress.setCity("Philadelphia");
        newAddress.setState("PA");
        newAddress.setPostalCode("19103");

        Member newMember = new Member();
        newMember.setFirstName("Jon");
        newMember.setLastName("Tran");
        newMember.setAddress(newAddress);

        // When
        libraryService.saveMember(newMember);
        Member savedMember = libraryService.getMember(6);

        try {
            libraryService.deleteMember(6);
            Member member = libraryService.getMember(6);
        } catch (DataIntegrityViolationException e) {
            actualException = e.getMessage();
        } catch (RuntimeException e) {
            actualException = e.getMessage();
        }

        // Then
        assertEquals(newMember, savedMember);
        assertEquals(expectedException, actualException);
    }

    @Test
    void getAllCheckOutTransactions() {
        // Given
        Integer numberOfTransactions = 8;

        // When
        List<CheckOutTransaction> checkOutTransactionList = checkOutTransactionService.getAllCheckOutTransactions();

        // Then
        assertEquals(numberOfTransactions, checkOutTransactionList.size());
    }

    @Test
    void getAllCheckOutTransactionsNotReturned() {
        // Given
        Integer numberOfTransactionsNotReturned = 4;

        // When
        List<CheckOutTransaction> checkOutTransactionsNotReturnedList = checkOutTransactionService.getAllCheckOutTransactionsNotReturned();

        // Then
        assertEquals(numberOfTransactionsNotReturned, checkOutTransactionsNotReturnedList.size());
    }

    @Test
    void saveCheckOutTransaction() {
        // Given
        CheckOutTransaction newCheckOutTransaction = new CheckOutTransaction();
        newCheckOutTransaction.setBookInstanceID(27);
        newCheckOutTransaction.setMemberID(1);
        newCheckOutTransaction.setDateCheckedOut("2020-06-13");

        // When
        checkOutTransactionService.saveCheckOutTransaction(newCheckOutTransaction);
        CheckOutTransaction savedCheckOutTransaction = checkOutTransactionService.getCheckOutTransaction(9);

        // Then
        assertTrue(newCheckOutTransaction.equals(savedCheckOutTransaction));
    }

    @Test
    void deleteCheckOutTransaction() {
        // When
        checkOutTransactionService.deleteCheckOutTransaction(1);
        checkOutTransactionService.deleteCheckOutTransaction(2);
        checkOutTransactionService.deleteCheckOutTransaction(3);
        checkOutTransactionService.deleteCheckOutTransaction(5);

        List<CheckOutTransaction> checkOutTransactionList = checkOutTransactionService.getAllCheckOutTransactions();

        // Then
        assertEquals(4, checkOutTransactionList.size());
    }

    @Test
    void isBookInstanceCheckedOut() {
        // When
        Boolean isCheckedOut = checkOutTransactionService.isBookInstanceCheckedOut(22);
        Boolean isNotCheckedOut = checkOutTransactionService.isBookInstanceCheckedOut(1);

        // Then
        assertTrue(isCheckedOut);
        assertFalse(isNotCheckedOut);
    }

}