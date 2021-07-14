package com.sdouglass.librarybe.book.controller;

import com.sdouglass.librarybe.book.entity.Book;
import com.sdouglass.librarybe.book.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/book")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/{id}")
    public Book getBook(@PathVariable Integer id) {
        return bookService.getBook(id);
    }

    @GetMapping("")
    public List<Book> getBooks() {
        return bookService.getAllBooks();
    }

    @PostMapping("")
    public Book addBook(@RequestBody Book book) {
        bookService.saveBook(book);
        return book;
    }

    @PutMapping("")
    public Book updateBook(@RequestBody Book book) {
        bookService.saveBook(book);
        return book;
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable Integer id) {
        return bookService.deleteBook(id);
    }
}
