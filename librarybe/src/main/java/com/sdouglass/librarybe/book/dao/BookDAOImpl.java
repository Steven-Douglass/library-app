package com.sdouglass.librarybe.book.dao;

import com.sdouglass.librarybe.book.entity.Book;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class BookDAOImpl implements BookDAO {

    private final EntityManager entityManager;

    @Autowired
    public BookDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
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
}
