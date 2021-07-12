package com.sdouglass.librarybe.author.service;

import com.sdouglass.librarybe.author.entity.Author;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AuthorService {
    public Author getAuthor(Integer id);
    public List<Author> getAllAuthors();
    public void saveAuthor(Author author);
    public String deleteAuthor(Integer id);
}
