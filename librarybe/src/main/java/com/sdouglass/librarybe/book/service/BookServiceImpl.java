package com.sdouglass.librarybe.book.service;

import com.sdouglass.librarybe.book.dao.BookDAO;
import com.sdouglass.librarybe.book.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookDAO bookDAO;

    @Autowired
    public BookServiceImpl(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    @Override
    @Transactional
    public Book getBook(Integer id) {
        Book book = bookDAO.getBook(id);
        if (book == null) {
            throw new RuntimeException("Book ID not found - " + id);
        }
        return book;
    }

    @Override
    @Transactional
    public List<Book> getAllBooks() {
        return bookDAO.getAllBooks();
    }

    @Override
    @Transactional
    public void saveBook(Book book) {
        try {
            bookDAO.saveBook(book);
        } catch (Exception e) {
            System.out.println("Exception caught when saving book: " + e);
        }
    }

    @Override
    @Transactional
    public String deleteBook(Integer id) {
        Book book = getBook(id);
        bookDAO.deleteBook(book.getBookID());
        return "Deleted book with ID: " + id;
    }

}
