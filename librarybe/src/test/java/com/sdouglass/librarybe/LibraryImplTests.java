package com.sdouglass.librarybe;

import com.sdouglass.librarybe.book.entity.Book;
import com.sdouglass.librarybe.bookauthor.entity.BookAuthor;
import com.sdouglass.librarybe.bookauthor.service.BookAuthorService;
import com.sdouglass.librarybe.bookinstance.entity.BookInstance;
import com.sdouglass.librarybe.bookinstance.service.BookInstanceService;
import com.sdouglass.librarybe.checkintransaction.entity.CheckInTransaction;
import com.sdouglass.librarybe.checkintransaction.service.CheckInTransactionService;
import com.sdouglass.librarybe.library.service.LibraryService;
import com.sdouglass.librarybe.librarymember.entity.LibraryMember;
import com.sdouglass.librarybe.librarymember.service.LibraryMemberService;
import com.sdouglass.librarybe.address.entity.Address;
import com.sdouglass.librarybe.address.service.AddressService;
import com.sdouglass.librarybe.author.entity.Author;
import com.sdouglass.librarybe.author.service.AuthorService;
import com.sdouglass.librarybe.book.service.BookService;
import com.sdouglass.librarybe.checkouttransaction.entity.CheckOutTransaction;
import com.sdouglass.librarybe.checkouttransaction.service.CheckOutTransactionService;
import com.sdouglass.librarybe.library.entity.Library;
import com.sdouglass.librarybe.member.entity.Member;
import com.sdouglass.librarybe.member.service.MemberService;
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
    private AddressService addressService;
    @Autowired
    private AuthorService authorService;
    @Autowired
    private BookService bookService;
    @Autowired
    private BookAuthorService bookAuthorService;
    @Autowired
    private BookInstanceService bookInstanceService;
    @Autowired
    private CheckOutTransactionService checkOutTransactionService;
    @Autowired
    private LibraryService libraryService;
    @Autowired
    private LibraryMemberService libraryMemberService;
    @Autowired
    private MemberService memberService;

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
        String expectedException = "Address ID not found: 9";
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
        String expectedException = "Author ID not found: 5";
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
        Book actualBook = bookService.getBook(1);

        // Then
        assertTrue(expectedBook.equals(actualBook));
    }

    @Test
    @Order(10)
    void getAllBooks() {
        // When
        List<Book> books = bookService.getAllBooks();

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
        bookService.saveBook(newBook);
        Book savedBook = bookService.getBook(11);

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
        bookService.saveBook(newBook);
        Book savedBook = bookService.getBook(11);

        bookService.deleteBook(11);
        try {
            Book book = bookService.getBook(11);
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
        String expectedBookInstanceException = "BookInstance ID not found: 22";
        String actualBookInstanceException = "";
        String expectedCheckOutTransactionException = "CheckOutTransaction ID not found: 1";
        String actualCheckOutTransactionException = "";
        String expectedLibraryMemberException = "LibraryMember ID not found: 6";
        String actualLibraryMemberException = "";
        String expectedLibraryException = "Library ID not found: 1";
        String actualLibraryException = "";
        String expectedAddressException = "Address ID not found: 5";
        String actualAddressException = "";

        // When
        libraryService.deleteLibrary(1);

        try {
            bookInstanceService.getBookInstance(22);
        } catch (Exception e) {
            actualBookInstanceException = e.getMessage();
        }

        try {
            checkOutTransactionService.getCheckOutTransaction(1);
        } catch (Exception e) {
            actualCheckOutTransactionException = e.getMessage();
        }

        try {
            libraryMemberService.getLibraryMember(6);
        } catch (Exception e) {
            actualLibraryMemberException = e.getMessage();
        }

        try {
            libraryService.getLibrary(1);
        } catch (RuntimeException e) {
            actualLibraryException = e.getMessage();
        }

        try {
            addressService.getAddress(5);
        } catch (Exception e) {
            actualAddressException = e.getMessage();
        }

        // Then
        assertEquals(expectedLibraryException, actualLibraryException);
        assertEquals(expectedCheckOutTransactionException, actualCheckOutTransactionException);
        assertEquals(expectedBookInstanceException, actualBookInstanceException);
        assertEquals(expectedLibraryMemberException, actualLibraryMemberException);
        assertEquals(expectedAddressException, actualAddressException);
    }

    @Test
    void deleteLibraryThatDoesNotExist() {
        // Given
        String expectedException = "Library ID not found: 7";
        String actualException = "";

        // When
        try {
            libraryService.deleteLibrary(7);
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

        // When
        Member actualMember = memberService.getMember(1);

        // Then
        assertTrue(expectedMember.equals(actualMember));
    }

    @Test
    @Order(18)
    void getAllMembers() {
        // When
        List<Member> members = memberService.getAllMembers();

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
        memberService.saveMember(newMember);
        Member savedMember = memberService.getMember(7);

        // Then
        assertTrue(newMember.equals(savedMember));
    }

    @Test
    @Order(20)
    void deleteMember() {
        // Given
        String expectedMemberException = "Member ID not found: 1";
        String actualMemberException = "";
        String expectedAddressException = "Address ID not found: 1";
        String actualAddressException = "";
        String expectedLibraryMemberException1 = "LibraryMember ID not found: 1";
        String actualLibraryMemberException1 = "";
        String expectedLibraryMemberException2 = "LibraryMember ID not found: 2";
        String actualLibraryMemberException2 = "";
        String expectedCheckOutTransactionException1 = "CheckOutTransaction ID not found: 1";
        String actualCheckOutTransactionException1 = "";
        String expectedCheckOutTransactionException2 = "CheckOutTransaction ID not found: 3";
        String actualCheckOutTransactionException2 = "";
        String expectedCheckOutTransactionException3 = "CheckOutTransaction ID not found: 4";
        String actualCheckOutTransactionException3 = "";

        // When
        try {
            memberService.deleteMember(1);
            Member member = memberService.getMember(1);
        } catch (DataIntegrityViolationException e) {
            actualMemberException = e.getMessage();
        } catch (RuntimeException e) {
            actualMemberException = e.getMessage();
        }

        try {
            Address address = addressService.getAddress(1);
        } catch (DataIntegrityViolationException e) {
            actualAddressException = e.getMessage();
        } catch (RuntimeException e) {
            actualAddressException = e.getMessage();
        }

        try {
            LibraryMember libraryMember = libraryMemberService.getLibraryMember(1);
        } catch (DataIntegrityViolationException e) {
            actualLibraryMemberException1 = e.getMessage();
        } catch (RuntimeException e) {
            actualLibraryMemberException1 = e.getMessage();
        }

        try {
            LibraryMember libraryMember = libraryMemberService.getLibraryMember(2);
        } catch (DataIntegrityViolationException e) {
            actualLibraryMemberException2 = e.getMessage();
        } catch (RuntimeException e) {
            actualLibraryMemberException2 = e.getMessage();
        }

        try {
            CheckOutTransaction checkOutTransaction = checkOutTransactionService.getCheckOutTransaction(1);
        } catch (DataIntegrityViolationException e) {
            actualCheckOutTransactionException1 = e.getMessage();
        } catch (RuntimeException e) {
            actualCheckOutTransactionException1 = e.getMessage();
        }

        try {
            CheckOutTransaction checkOutTransaction = checkOutTransactionService.getCheckOutTransaction(3);
        } catch (DataIntegrityViolationException e) {
            actualCheckOutTransactionException2 = e.getMessage();
        } catch (RuntimeException e) {
            actualCheckOutTransactionException2 = e.getMessage();
        }

        try {
            CheckOutTransaction checkOutTransaction = checkOutTransactionService.getCheckOutTransaction(4);
        } catch (DataIntegrityViolationException e) {
            actualCheckOutTransactionException3 = e.getMessage();
        } catch (RuntimeException e) {
            actualCheckOutTransactionException3 = e.getMessage();
        }

        // Then
        assertEquals(expectedMemberException, actualMemberException);
        assertEquals(expectedAddressException, actualAddressException);
        assertEquals(expectedLibraryMemberException1, actualLibraryMemberException1);
        assertEquals(expectedLibraryMemberException2, actualLibraryMemberException2);
        assertEquals(expectedCheckOutTransactionException1, actualCheckOutTransactionException1);
        assertEquals(expectedCheckOutTransactionException2, actualCheckOutTransactionException2);
        assertEquals(expectedCheckOutTransactionException3, actualCheckOutTransactionException3);
    }

    @Test
    void getAllCheckOutTransactions() {
        // Given
        Integer numberOfTransactions = 9;

        // When
        List<CheckOutTransaction> checkOutTransactionList = checkOutTransactionService.getAllCheckOutTransactions();

        // Then
        assertEquals(numberOfTransactions, checkOutTransactionList.size());
    }

    @Test
    void getAllCheckOutTransactionsNotReturned() {
        // Given
        Integer numberOfTransactionsNotReturned = 5;

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
        CheckOutTransaction savedCheckOutTransaction = checkOutTransactionService.getCheckOutTransaction(10);

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
        assertEquals(5, checkOutTransactionList.size());
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

    @Test
    void getCheckedOutTransactionNotReturnedForBook() {
        // Given
        CheckOutTransaction expectedCheckOutTransaction = checkOutTransactionService.getCheckOutTransaction(9);

        // When
        CheckOutTransaction actualCheckOutTransaction = checkOutTransactionService.getCheckedOutTransactionNotReturnedForBook(22);
        CheckOutTransaction checkOutTransactionNull = checkOutTransactionService.getCheckedOutTransactionNotReturnedForBook(1);

        // Then
        assertEquals(expectedCheckOutTransaction, actualCheckOutTransaction);
        assertNull(checkOutTransactionNull);
    }

    @Test
    void getAllCheckOutTransactionsForMember() {
        // Given
        Integer memberId = 1;
        Integer totalTransactions = 3;
        Integer booksCurrentlyCheckedOut = 1;

        // When
        List<CheckOutTransaction> checkOutTransactions = checkOutTransactionService
                .getAllCheckOutTransactionsForMember(memberId);
        List<CheckOutTransaction> checkedOutTransactionsNotReturned = checkOutTransactionService
                .getAllCheckedOutTransactionsNotReturnedForMember(memberId);

        // Then
        assertEquals(totalTransactions, checkOutTransactions.size());
        assertEquals(booksCurrentlyCheckedOut, checkedOutTransactionsNotReturned.size());
    }

    @Test
    void getBookAuthor() {
        // Given
        BookAuthor expectedBookAuthor = new BookAuthor();
        expectedBookAuthor.setBookAuthorID(1);
        expectedBookAuthor.setBookID(1);
        expectedBookAuthor.setAuthorID(4);
        String expectedException = "BookAuthor ID not found: 19";
        String actualException = "";

        // When
        BookAuthor actualBookAuthor = bookAuthorService.getBookAuthor(1);
        try {
            bookAuthorService.getBookAuthor(19);
        } catch (Exception e) {
            actualException = e.getMessage();
        }

        // Then
        assertEquals(expectedBookAuthor, actualBookAuthor);
        assertEquals(expectedException, actualException);
    }

    @Test
    void getAllBookAuthors() {
        // Given
        Integer numBookAuthors = 10;

        // When
        List<BookAuthor> bookAuthorList = bookAuthorService.getAllBookAuthors();

        // Then
        assertEquals(numBookAuthors, bookAuthorList.size());
    }

    @Test
    void saveBookAuthor() {
        // Given
        Book newTextBook = new Book();
        newTextBook.setTitle("Science textbook");
        newTextBook.setPublishDate("2014-11-23");
        bookService.saveBook(newTextBook);

        Author newAuthor1 = new Author();
        newAuthor1.setFirstName("Philip");
        newAuthor1.setLastName("Wu");
        authorService.saveAuthor(newAuthor1);

        Author newAuthor2 = new Author();
        newAuthor2.setFirstName("Monica");
        newAuthor2.setLastName("Garcia");
        authorService.saveAuthor(newAuthor2);

        BookAuthor newBookAuthor1 = new BookAuthor();
        newBookAuthor1.setBookID(11);
        newBookAuthor1.setAuthorID(5);
        bookAuthorService.saveBookAuthor(newBookAuthor1);

        BookAuthor newBookAuthor2 = new BookAuthor();
        newBookAuthor2.setBookID(11);
        newBookAuthor2.setAuthorID(6);
        bookAuthorService.saveBookAuthor(newBookAuthor2);

        // When
        BookAuthor savedBookAuthor1 = bookAuthorService.getBookAuthor(11);
        BookAuthor savedBookAuthor2 = bookAuthorService.getBookAuthor(12);

        // Then
        assertEquals(newBookAuthor1, savedBookAuthor1);
        assertEquals(newBookAuthor2, savedBookAuthor2);
    }

    @Test
    void deleteBookAuthor() {
        // Given
        String expectedException = "BookAuthor ID not found: 11";
        String actualException = "";
        String expectedDeleteMessage = "Deleted BookAuthor with ID: 11";
        String actualDeleteMessage = "";
        
        BookAuthor newBookAuthor = new BookAuthor();
        newBookAuthor.setBookID(10);
        newBookAuthor.setAuthorID(4);

        // When
        bookAuthorService.saveBookAuthor(newBookAuthor);
        BookAuthor savedBookAuthor = bookAuthorService.getBookAuthor(11);
        actualDeleteMessage = bookAuthorService.deleteBookAuthor(11);

        try {
            BookAuthor bookAuthor = bookAuthorService.getBookAuthor(11);
        } catch (RuntimeException e) {
            actualException = e.getMessage();
        }

        // Then
        assertEquals(expectedException, actualException);
        assertEquals(expectedDeleteMessage, actualDeleteMessage);
    }


    @Test
    void getBookInstance() {
        // Given
        BookInstance expectedBookInstance = new BookInstance();
        expectedBookInstance.setBookInstanceID(30);
        expectedBookInstance.setBookID(10);
        expectedBookInstance.setLibraryID(2);

        String expectedException = "BookInstance ID not found: 31";
        String actualException = "";

        // When
        BookInstance actualBookInstance = bookInstanceService.getBookInstance(30);

        try {
            bookInstanceService.getBookInstance(31);
        } catch (Exception e) {
            actualException = e.getMessage();
        }

        // Then
        assertEquals(expectedBookInstance, actualBookInstance);
        assertEquals(expectedException, actualException);
    }

    @Test
    void getAllBookInstances() {
        // Given
        Integer numBookInstances = 30;

        // When
        List<BookInstance> bookInstances = bookInstanceService.getAllBookInstances();

        // Then
        assertEquals(numBookInstances, bookInstances.size());
    }

    @Test
    void saveBookInstance() {
        // Given
        BookInstance newBookInstance = new BookInstance();
        newBookInstance.setBookID(1);
        newBookInstance.setLibraryID(1);

        // When
        bookInstanceService.saveBookInstance(newBookInstance);
        BookInstance savedBookInstance = bookInstanceService.getBookInstance(31);

        // Then
        assertEquals(newBookInstance, savedBookInstance);
    }

    @Test
    void deleteBookInstance() {
        // Given
        String expectedException = "BookInstance ID not found: 22";
        String actualException = "";
        String expectedDeleteMessage = "Deleted BookInstance with ID: 22";
        String actualDeleteMessage = "";
        String expectedCheckOutTransactionMessage1 = "CheckOutTransaction ID not found: 1";
        String actualCheckOutTransactionMessage1 = "";
        String expectedCheckOutTransactionMessage2 = "CheckOutTransaction ID not found: 9";
        String actualCheckOutTransactionMessage2 = "";
        String expectedDeleteWhenBookDoesNotExistMessage = "BookInstance ID not found: 22";
        String actualDeleteWhenBookDoesNotExistMessage = "";

        // When
        actualDeleteMessage = bookInstanceService.deleteBookInstance(22);

        try {
            bookInstanceService.getBookInstance(22);
        } catch (Exception e) {
            actualException = e.getMessage();
        }

        try {
            checkOutTransactionService.getCheckOutTransaction(1);
        } catch (Exception e) {
            actualCheckOutTransactionMessage1 = e.getMessage();
        }

        try {
            checkOutTransactionService.getCheckOutTransaction(9);
        } catch (Exception e) {
            actualCheckOutTransactionMessage2 = e.getMessage();
        }

        try {
            bookInstanceService.deleteBookInstance(22);
        } catch (Exception e) {
            actualDeleteWhenBookDoesNotExistMessage = e.getMessage();
        }

        // Then
        assertEquals(expectedException, actualException);
        assertEquals(expectedDeleteMessage, actualDeleteMessage);
        assertEquals(expectedCheckOutTransactionMessage1, actualCheckOutTransactionMessage1);
        assertEquals(expectedCheckOutTransactionMessage2, actualCheckOutTransactionMessage2);
        assertEquals(expectedDeleteWhenBookDoesNotExistMessage, actualDeleteWhenBookDoesNotExistMessage);
    }

    @Test
    void getAllBookInstancesForLibrary() {
        // Given
        Integer expectedNumberOfBookInstances = 13;

        // When
        List<BookInstance> bookInstances = bookInstanceService.getAllBookInstancesForLibrary(1);

        // Then
        assertEquals(expectedNumberOfBookInstances, bookInstances.size());
    }

}