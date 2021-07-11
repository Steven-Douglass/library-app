package com.sdouglass.librarybe.controller;

import com.sdouglass.librarybe.entity.Book;
import com.sdouglass.librarybe.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class LibraryController {
    private final LibraryService libraryService;

    @Autowired
    public LibraryController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @GetMapping("login")
    public String login() {
        return "login";
    }

}
