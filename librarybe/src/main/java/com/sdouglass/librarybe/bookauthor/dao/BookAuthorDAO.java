package com.sdouglass.librarybe.bookauthor.dao;

import com.sdouglass.librarybe.bookauthor.entity.BookAuthor;

import java.util.List;

public interface BookAuthorDAO {
    public BookAuthor getBookAuthor(Integer id);
    public List<BookAuthor> getAllBookAuthors();
    public void saveBookAuthor(BookAuthor bookAuthor);
    public void deleteBookAuthor(Integer id);
}
