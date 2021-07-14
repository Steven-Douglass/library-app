package com.sdouglass.librarybe.book.service;

import com.sdouglass.librarybe.book.entity.Book;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookService {
    public Book getBook(Integer id);
    public List<Book> getAllBooks();
    public void saveBook(Book book);
    public String deleteBook(Integer id);
}
