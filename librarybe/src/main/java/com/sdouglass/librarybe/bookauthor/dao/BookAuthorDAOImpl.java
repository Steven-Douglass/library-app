package com.sdouglass.librarybe.bookauthor.dao;

import com.sdouglass.librarybe.bookauthor.entity.BookAuthor;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class BookAuthorDAOImpl implements BookAuthorDAO {

    private final EntityManager entityManager;

    @Autowired
    public BookAuthorDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public BookAuthor getBookAuthor(Integer id) {
        Session currentSession = entityManager.unwrap(Session.class);
        return currentSession.get(BookAuthor.class, id);
    }

    @Override
    public List<BookAuthor> getAllBookAuthors() {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<BookAuthor> bookAuthorQuery = currentSession.createQuery("FROM BookAuthor", BookAuthor.class);
        List<BookAuthor> bookAuthors = bookAuthorQuery.getResultList();
        return bookAuthors;
    }

    @Override
    public void saveBookAuthor(BookAuthor bookAuthor) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.saveOrUpdate(bookAuthor);
    }

    @Override
    public void deleteBookAuthor(Integer id) {
        entityManager.unwrap(Session.class)
                .createQuery("DELETE FROM BookAuthor ba WHERE ba.bookAuthorID = :id")
                .setParameter("id", id)
                .executeUpdate();
    }
}
