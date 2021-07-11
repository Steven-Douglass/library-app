package com.sdouglass.librarybe.dao;

import com.sdouglass.librarybe.entity.*;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class LibraryDAOImpl implements LibraryDAO {

    private EntityManager entityManager;

    @Autowired
    public LibraryDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Address getAddress(Integer id) {
        Session currentSession = entityManager.unwrap(Session.class);
        return currentSession.get(Address.class, id);
    }

    @Override
    public List<Address> getAllAddresses() {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Address> addressQuery = currentSession.createQuery("FROM Address", Address.class);
        List<Address> addresses = addressQuery.getResultList();
        return addresses;
    }

    @Override
    public void saveAddress(Address address) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.saveOrUpdate(address);
    }

    @Override
    public void deleteAddress(Integer id) {
        // Get list of libraries who use this address
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Library> libraryQuery = currentSession.createQuery("FROM Library l WHERE l.addressID = :id");
        libraryQuery.setParameter("id", id);
        List<Library> libraryList = libraryQuery.getResultList();
        List<Integer> libraryIDs = libraryList.stream().map(Library::getLibraryID).collect(Collectors.toList());

        // Remove the address reference of any library with this address
        if (!libraryIDs.isEmpty()) {
            Query<Library> libraryAddressQuery = currentSession.createQuery("UPDATE Library set addressID = null " +
                                                                             "WHERE id IN :libraryIDs");
            libraryAddressQuery.setParameter("libraryIDs", libraryIDs);
            libraryAddressQuery.executeUpdate();
        }

        // Get list of members who use this address
        Query<Member> memberQuery = currentSession.createQuery("FROM Member m WHERE m.addressID = :id");
        memberQuery.setParameter("id", id);
        List<Member> memberList = memberQuery.getResultList();
        List<Integer> memberIDs = memberList.stream().map(Member::getMemberID).collect(Collectors.toList());

        // Remove the address reference of any member with this address
        if (!memberIDs.isEmpty()) {
            Query<Library> memberAddressQuery = currentSession.createQuery("UPDATE Member set addressID = null " +
                                                                           "WHERE id IN :memberIDs");
            memberAddressQuery.setParameter("memberIDs", memberIDs);
            memberAddressQuery.executeUpdate();
        }

        // Delete the address
        Query addressQuery = currentSession.createQuery("DELETE FROM Address a WHERE a.addressID = :id");
        addressQuery.setParameter("id", id);
        addressQuery.executeUpdate();
    }

    @Override
    public Author getAuthor(Integer id) {
        Session currentSession = entityManager.unwrap(Session.class);
        return currentSession.get(Author.class, id);
    }

    @Override
    public List<Author> getAllAuthors() {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Author> authorQuery = currentSession.createQuery("FROM Author", Author.class);
        List<Author> authors = authorQuery.getResultList();
        return authors;
    }

    @Override
    public void saveAuthor(Author author) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.saveOrUpdate(author);
    }

    @Override
    public void deleteAuthor(Integer id) {
        // Get list of Book IDs that will need to be deleted when the author is deleted
        Session currentSession = entityManager.unwrap(Session.class);
        Query<BookAuthor> bookAuthorQuery = currentSession.createQuery("FROM BookAuthor ba WHERE ba.authorID = :id");
        bookAuthorQuery.setParameter("id", id);
        List<BookAuthor> bookAuthorList = bookAuthorQuery.getResultList();
        List<Integer> bookIDs = bookAuthorList.stream().map(BookAuthor::getBookID).collect(Collectors.toList());

        // Delete the BookAuthor bridge table entry
        Query bookAuthorDeleteQuery = currentSession.createQuery("DELETE FROM BookAuthor ba WHERE ba.authorID = :id");
        bookAuthorDeleteQuery.setParameter("id", id);
        bookAuthorDeleteQuery.executeUpdate();

        // Delete the Author table entry
        Query authorQuery = currentSession.createQuery("DELETE FROM Author a WHERE a.authorID = :id");
        authorQuery.setParameter("id", id);
        authorQuery.executeUpdate();

        // Delete the books written by the author from the Book table
        Query bookQuery = currentSession.createQuery("DELETE FROM Book b WHERE b.bookID IN :bookIDs");
        bookQuery.setParameter("bookIDs", bookIDs);
        bookQuery.executeUpdate();
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
        deleteAddress(library.getAddressID());
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
        // Get a list of book IDs the member has checked out
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Book> bookQuery = currentSession.createQuery("FROM Book b WHERE b.memberID = :id", Book.class);
        bookQuery.setParameter("id", id);
        List<Book> books = bookQuery.getResultList();
        List<Integer> bookIDs = books.stream().map(Book::getBookID).collect(Collectors.toList());

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