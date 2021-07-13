package com.sdouglass.librarybe.dao;

import com.sdouglass.librarybe.address.service.AddressService;
import com.sdouglass.librarybe.checkouttransaction.entity.CheckOutTransaction;
import com.sdouglass.librarybe.entity.*;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class LibraryDAOImpl implements LibraryDAO {

    private EntityManager entityManager;
    private AddressService addressService;

    @Autowired
    public LibraryDAOImpl(EntityManager entityManager, AddressService addressService) {
        this.entityManager = entityManager;
        this.addressService = addressService;
    }

    @Override
    public Book getBook(Integer id) {
        Session currentSession = entityManager.unwrap(Session.class);
        return currentSession.get(Book.class, id);
    }

    @Override
    public List<Book> getAllBooks() {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Book> bookQuery = currentSession.createQuery("FROM Book", Book.class);
        List<Book> books = bookQuery.getResultList();
        return books;
    }

    @Override
    public void saveBook(Book book) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.saveOrUpdate(book);
    }

    @Override
    public void deleteBook(Integer id) {
        // Delete the BookAuthor bridge table entry
        Session currentSession = entityManager.unwrap(Session.class);
        Query bookAuthorQuery = currentSession.createQuery("DELETE FROM BookAuthor ba WHERE ba.bookID=:id");
        bookAuthorQuery.setParameter("id", id);
        bookAuthorQuery.executeUpdate();

        // Delete the Book
        Query bookQuery = currentSession.createQuery("DELETE FROM Book b WHERE b.bookID=:id");
        bookQuery.setParameter("id", id);
        bookQuery.executeUpdate();
    }

    @Override
    public Library getLibrary(Integer id) {
        Session currentSession = entityManager.unwrap(Session.class);
        return currentSession.get(Library.class, id);
    }

    @Override
    public List<Library> getAllLibraries() {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Library> libraryQuery = currentSession.createQuery("FROM Library", Library.class);
        List<Library> libraries = libraryQuery.getResultList();
        return libraries;
    }

    @Override
    public void saveLibrary(Library library) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.saveOrUpdate(library);
    }

    @Override
    public void deleteLibrary(Integer id) {
        // Get list of Book IDs that will need to be deleted when the Library is deleted
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Book> bookQuery = currentSession.createQuery("FROM Book b WHERE b.libraryID = :id");
        bookQuery.setParameter("id", id);
        List<Book> bookList = bookQuery.getResultList();
        List<Integer> bookIDs = bookList.stream().map(Book::getBookID).collect(Collectors.toList());

        // Delete the BookAuthor entries for the books being deleted
        Query bookAuthorDeleteQuery = currentSession.createQuery("DELETE FROM BookAuthor ba WHERE ba.bookID IN :bookIDs");
        bookAuthorDeleteQuery.setParameter("bookIDs", bookIDs);
        bookAuthorDeleteQuery.executeUpdate();

        // Delete the books from the library
        Query bookDeleteQuery = currentSession.createQuery("DELETE FROM Book b WHERE b.bookID IN :bookIDs");
        bookDeleteQuery.setParameter("bookIDs", bookIDs);
        bookDeleteQuery.executeUpdate();

        // Delete the Library
        Library library = getLibrary(id);
        addressService.deleteAddress(library.getAddress().getAddressID());
        Query libraryQuery = currentSession.createQuery("DELETE FROM Library l WHERE l.libraryID = :id");
        libraryQuery.setParameter("id", id);
        libraryQuery.executeUpdate();
    }

    @Override
    public Member getMember(Integer id) {
        Session currentSession = entityManager.unwrap(Session.class);
        return currentSession.get(Member.class, id);
    }

    @Override
    public List<Member> getAllMembers() {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Member> memberQuery = currentSession.createQuery("FROM Member", Member.class);
        List<Member> members = memberQuery.getResultList();
        return members;
    }

    @Override
    public void saveMember(Member member) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.saveOrUpdate(member);
    }

    @Override
    public void deleteMember(Integer id) {
        // Get a list of book IDS the member has checked out
        Session currentSession = entityManager.unwrap(Session.class);

        // The below commented 3 lines work
//        Query<CheckOutTransaction> checkOutTransactionQuery = currentSession.createQuery("FROM CheckOutTransaction co " +
//                "WHERE co.memberID = :id", CheckOutTransaction.class).setParameter("id", id);
//        List<CheckOutTransaction> checkOutTransactions = checkOutTransactionQuery.getResultList();

        try {
            Query<CheckOutTransaction> checkOutTransactionQuery = currentSession.createQuery(
                    "FROM CheckOutTransaction co \n" +
                    "LEFT JOIN CheckInTransaction ci ON co.checkOutTransactionID = ci.checkOutTransactionID \n" +
                    "WHERE co.memberID = :id", CheckOutTransaction.class).setParameter("id", id);
            List<CheckOutTransaction> checkOutTransactions = checkOutTransactionQuery.getResultList();
            System.out.println("Breakpoint here");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


        /*
        DELETE FROM BookAuthor ba WHERE ba.bookID=:id
         */


        List<Integer> bookIDs = new ArrayList<>();
        // Get a list of book IDs the member has checked out
//        Query<Book> bookQuery = currentSession.createQuery("FROM Book b WHERE b.memberID = :id", Book.class);
//        bookQuery.setParameter("id", id);
//        List<Book> books = bookQuery.getResultList();
//        List<Integer> bookIDs = books.stream().map(Book::getBookID).collect(Collectors.toList());

        // Remove the MemberID entry for the book to indicate it is no longer checked out by the member
        if (!bookIDs.isEmpty()) {
            Query<Book> bookUpdateQuery = currentSession.createQuery("UPDATE Book b set b.memberID = null " +
                    "WHERE id IN :bookIDs");
            bookUpdateQuery.setParameter("bookIDs", bookIDs);
            bookUpdateQuery.executeUpdate();
        }

        // Delete the member
        Query memberQuery = currentSession.createQuery("DELETE FROM Member m WHERE m.memberID = :id");
        memberQuery.setParameter("id", id);
        memberQuery.executeUpdate();
    }
}
