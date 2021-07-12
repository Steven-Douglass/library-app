package com.sdouglass.librarybe.author.dao;

import com.sdouglass.librarybe.author.entity.Author;

import java.util.List;

public interface AuthorDAO {
    public Author getAuthor(Integer id);
    public List<Author> getAllAuthors();
    public void saveAuthor(Author author);
    public void deleteAuthor(Integer id);
}
