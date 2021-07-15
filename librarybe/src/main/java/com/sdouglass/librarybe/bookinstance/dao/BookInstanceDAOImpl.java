package com.sdouglass.librarybe.bookinstance.dao;

import com.sdouglass.librarybe.bookinstance.entity.BookInstance;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class BookInstanceDAOImpl implements BookInstanceDAO {

    private final EntityManager entityManager;

    @Autowired
    public BookInstanceDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public BookInstance getBookInstance(Integer id) {
        Session currentSession = entityManager.unwrap(Session.class);
        return currentSession.get(BookInstance.class, id);
    }

    @Override
    public List<BookInstance> getAllBookInstances() {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<BookInstance> bookInstanceQuery = currentSession.createQuery("FROM BookInstance", BookInstance.class);
        List<BookInstance> bookInstances = bookInstanceQuery.getResultList();
        return bookInstances;
    }

    @Override
    public void saveBookInstance(BookInstance bookInstance) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.saveOrUpdate(bookInstance);
    }

    @Override
    public void deleteBookInstance(Integer id) {
        entityManager.unwrap(Session.class)
                .createQuery("DELETE FROM BookInstance bi WHERE bi.bookInstanceID = :id")
                .setParameter("id", id)
                .executeUpdate();
    }
}
