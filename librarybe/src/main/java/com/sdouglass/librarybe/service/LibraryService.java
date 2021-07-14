package com.sdouglass.librarybe.service;

import com.sdouglass.librarybe.entity.*;
import com.sdouglass.librarybe.member.entity.Member;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LibraryService {
    public Book getBook(Integer id);
    public List<Book> getAllBooks();
    public void saveBook(Book book);
    public String deleteBook(Integer id);

    public Library getLibrary(Integer id);
    public List<Library> getAllLibraries();
    public void saveLibrary(Library library);
    public String deleteLibrary(Integer id);
}
