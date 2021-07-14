package com.sdouglass.librarybe.author.dao;

import com.sdouglass.librarybe.author.entity.Author;
import com.sdouglass.librarybe.bookauthor.entity.BookAuthor;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class AuthorDAOImpl implements AuthorDAO {

    private final EntityManager entityManager;

    @Autowired
    public AuthorDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
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
}
