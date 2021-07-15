package com.sdouglass.librarybe.bookauthor.service;

import com.sdouglass.librarybe.book.entity.Book;
import com.sdouglass.librarybe.bookauthor.dao.BookAuthorDAO;
import com.sdouglass.librarybe.bookauthor.entity.BookAuthor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BookAuthorServiceImpl implements BookAuthorService {

    private final BookAuthorDAO bookAuthorDAO;

    @Autowired
    public BookAuthorServiceImpl(BookAuthorDAO bookAuthorDAO) {
        this.bookAuthorDAO = bookAuthorDAO;
    }

    @Override
    @Transactional
    public BookAuthor getBookAuthor(Integer id) {
        Optional<BookAuthor> bookAuthor = Optional.ofNullable(bookAuthorDAO.getBookAuthor(id));
        return bookAuthor.orElseThrow(() -> new RuntimeException("BookAuthor ID not found - " + id));
    }

    @Override
    @Transactional
    public List<BookAuthor> getAllBookAuthors() {
        return bookAuthorDAO.getAllBookAuthors();
    }

    @Override
    @Transactional
    public void saveBookAuthor(BookAuthor bookAuthor) {
        try {
            bookAuthorDAO.saveBookAuthor(bookAuthor);
        } catch (Exception e) {
            System.out.println("Exception caught when saving BookAuthor: " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public String deleteBookAuthor(Integer id) {
        BookAuthor bookAuthor = getBookAuthor(id);
        bookAuthorDAO.deleteBookAuthor(bookAuthor.getBookAuthorID());
        return "Deleted BookAuthor with ID: " + id;
    }
}
