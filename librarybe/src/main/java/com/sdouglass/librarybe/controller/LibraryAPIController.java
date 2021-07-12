package com.sdouglass.librarybe.controller;

import com.sdouglass.librarybe.address.service.AddressService;
import com.sdouglass.librarybe.author.entity.Author;
import com.sdouglass.librarybe.author.service.AuthorService;
import com.sdouglass.librarybe.entity.*;
import com.sdouglass.librarybe.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class LibraryAPIController {
    private final LibraryService libraryService;
    private final AddressService addressService;
    private final AuthorService authorService;

    @Autowired
    public LibraryAPIController(LibraryService libraryService, AddressService addressService,
                                AuthorService authorService) {
        this.libraryService = libraryService;
        this.addressService = addressService;
        this.authorService = authorService;
    }

    @GetMapping("/book/{id}")
    public Book getBook(@PathVariable Integer id) {
        return libraryService.getBook(id);
    }

    @GetMapping("/book")
    public List<Book> getBooks() {
        return libraryService.getAllBooks();
    }

    @PostMapping("/book")
    public Book addBook(@RequestBody Book book) {
        libraryService.saveBook(book);
        return book;
    }

    @PutMapping("/book")
    public Book updateBook(@RequestBody Book book) {
        libraryService.saveBook(book);
        return book;
    }

    @DeleteMapping("/book/{id}")
    public String deleteBook(@PathVariable Integer id) {
        return libraryService.deleteBook(id);
    }

    @GetMapping("/library/{id}")
    public Library getLibrary(@PathVariable Integer id) {
        return libraryService.getLibrary(id);
    }

    @GetMapping("/library")
    public List<Library> getLibraries() {
        return libraryService.getAllLibraries();
    }

    @PostMapping("/library")
    public Library addLibrary(@RequestBody Library library) {
        libraryService.saveLibrary(library);
        return library;
    }

    @PutMapping("/library")
    public Library updateLibrary(@RequestBody Library library) {
        libraryService.saveLibrary(library);
        return library;
    }

    @DeleteMapping("/library/{id}")
    public String deleteLibrary(@PathVariable Integer id) {
        return libraryService.deleteLibrary(id);
    }

    @GetMapping("/member/{id}")
    public Member getMember(@PathVariable Integer id) {
        return libraryService.getMember(id);
    }

    @GetMapping("/member")
    public List<Member> getMembers() {
        return libraryService.getAllMembers();
    }

    @PostMapping("/member")
    public Member addMember(@RequestBody Member member) {
        libraryService.saveMember(member);
        return member;
    }

    @PutMapping("/member")
    public Member updateMember(@RequestBody Member member) {
        libraryService.saveMember(member);
        return member;
    }

    @DeleteMapping("/member/{id}")
    public String deleteMember(@PathVariable Integer id) {
        return libraryService.deleteMember(id);
    }

}
