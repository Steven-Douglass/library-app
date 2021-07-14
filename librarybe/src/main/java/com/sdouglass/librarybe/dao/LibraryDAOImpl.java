package com.sdouglass.librarybe.dao;

import com.sdouglass.librarybe.book.entity.Book;
import com.sdouglass.librarybe.address.service.AddressService;
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
    private AddressService addressService;

    @Autowired
    public LibraryDAOImpl(EntityManager entityManager, AddressService addressService) {
        this.entityManager = entityManager;
        this.addressService = addressService;
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
}
