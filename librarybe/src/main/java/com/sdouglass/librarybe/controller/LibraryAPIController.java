package com.sdouglass.librarybe.controller;

import com.sdouglass.librarybe.address.entity.Address;
import com.sdouglass.librarybe.address.service.AddressService;
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

    @Autowired
    public LibraryAPIController(LibraryService libraryService, AddressService addressService) {
        this.libraryService = libraryService;
        this.addressService = addressService;
    }

//    @GetMapping("/address/{id}")
//    public Address getAddress(@PathVariable Integer id) {
//        return addressService.getAddress(id);
//    }
//
//    @GetMapping("/address")
//    public List<Address> getAddresses() {
//        return addressService.getAllAddresses();
//    }
//
//    @PostMapping("/address")
//    public Address addAddress(@RequestBody Address address) {
//        addressService.saveAddress(address);
//        return address;
//    }
//
//    @PutMapping("/address")
//    public Address updateAddress(@RequestBody Address address) {
//        addressService.saveAddress(address);
//        return address;
//    }
//
//    @DeleteMapping("/address/{id}")
//    public String deleteAddress(@PathVariable Integer id) {
//        return addressService.deleteAddress(id);
//    }

    @GetMapping("/author/{id}")
    public Author getAuthor(@PathVariable Integer id) {
        return libraryService.getAuthor(id);
    }

    @GetMapping("/author")
    public List<Author> getAuthors() {
        return libraryService.getAllAuthors();
    }

    @PostMapping("/author")
    public Author addAuthor(@RequestBody Author author) {
        libraryService.saveAuthor(author);
        return author;
    }

    @PutMapping("/author")
    public Author updateAuthor(@RequestBody Author author) {
        libraryService.saveAuthor(author);
        return author;
    }

    @DeleteMapping("/author/{id}")
    public String deleteAuthor(@PathVariable Integer id) {
        return libraryService.deleteAuthor(id);
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
