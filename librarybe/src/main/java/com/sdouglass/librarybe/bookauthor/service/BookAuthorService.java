package com.sdouglass.librarybe.bookauthor.service;

import com.sdouglass.librarybe.bookauthor.entity.BookAuthor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookAuthorService {
    public BookAuthor getBookAuthor(Integer id);
    public List<BookAuthor> getAllBookAuthors();
    public void saveBookAuthor(BookAuthor bookAuthor);
    public String deleteBookAuthor(Integer id);
}
