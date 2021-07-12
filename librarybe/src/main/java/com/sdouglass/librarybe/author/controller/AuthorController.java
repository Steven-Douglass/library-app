package com.sdouglass.librarybe.author.controller;

import com.sdouglass.librarybe.author.entity.Author;
import com.sdouglass.librarybe.author.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/author")
public class AuthorController {
    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/{id}")
    public Author getAuthor(@PathVariable Integer id) {
        return authorService.getAuthor(id);
    }

    @GetMapping("")
    public List<Author> getAuthors() {
        return authorService.getAllAuthors();
    }

    @PostMapping("")
    public Author addAuthor(@RequestBody Author author) {
        authorService.saveAuthor(author);
        return author;
    }

    @PutMapping("")
    public Author updateAuthor(@RequestBody Author author) {
        authorService.saveAuthor(author);
        return author;
    }

    @DeleteMapping("/{id}")
    public String deleteAuthor(@PathVariable Integer id) {
        return authorService.deleteAuthor(id);
    }
}
