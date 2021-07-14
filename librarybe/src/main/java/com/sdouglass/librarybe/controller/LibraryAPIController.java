package com.sdouglass.librarybe.controller;

import com.sdouglass.librarybe.book.entity.Book;
import com.sdouglass.librarybe.address.service.AddressService;
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

}
