package com.sdouglass.librarybe.author.service;

import com.sdouglass.librarybe.author.dao.AuthorDAO;
import com.sdouglass.librarybe.author.entity.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorDAO authorDAO;

    @Autowired
    public AuthorServiceImpl(AuthorDAO authorDAO) {
        this.authorDAO = authorDAO;
    }

    @Override
    @Transactional
    public Author getAuthor(Integer id) {
        Author author = authorDAO.getAuthor(id);
        if (author == null) {
            throw new RuntimeException("Author ID not found: " + id);
        }
        return author;
    }

    @Override
    @Transactional
    public List<Author> getAllAuthors() {
        return authorDAO.getAllAuthors();
    }

    @Override
    @Transactional
    public void saveAuthor(Author author) {
        authorDAO.saveAuthor(author);
    }

    @Override
    @Transactional
    public String deleteAuthor(Integer id) {
        Author author = getAuthor(id);
        authorDAO.deleteAuthor(author.getAuthorID());
        return "Deleted author with ID: " + id;
    }
}
