package com.sdouglass.librarybe.book.dao;

import com.sdouglass.librarybe.book.entity.Book;

import java.util.List;

public interface BookDAO {
    public Book getBook(Integer id);
    public List<Book> getAllBooks();
    public void saveBook(Book book);
    public void deleteBook(Integer id);
}
