package com.sdouglass.librarybe.library.controller;

import com.sdouglass.librarybe.address.service.AddressService;
import com.sdouglass.librarybe.author.service.AuthorService;
import com.sdouglass.librarybe.library.entity.Library;
import com.sdouglass.librarybe.library.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/library")
public class LibraryController {
    private final LibraryService libraryService;

    @Autowired
    public LibraryController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @GetMapping("/{id}")
    public Library getLibrary(@PathVariable Integer id) {
        return libraryService.getLibrary(id);
    }

    @GetMapping("")
    public List<Library> getLibraries() {
        return libraryService.getAllLibraries();
    }

    @PostMapping("")
    public Library addLibrary(@RequestBody Library library) {
        libraryService.saveLibrary(library);
        return library;
    }

    @PutMapping("")
    public Library updateLibrary(@RequestBody Library library) {
        libraryService.saveLibrary(library);
        return library;
    }

    @DeleteMapping("/{id}")
    public String deleteLibrary(@PathVariable Integer id) {
        return libraryService.deleteLibrary(id);
    }
}
