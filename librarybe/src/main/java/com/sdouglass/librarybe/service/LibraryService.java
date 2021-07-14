package com.sdouglass.librarybe.service;

import com.sdouglass.librarybe.book.entity.Book;
import com.sdouglass.librarybe.entity.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LibraryService {
    public Library getLibrary(Integer id);
    public List<Library> getAllLibraries();
    public void saveLibrary(Library library);
    public String deleteLibrary(Integer id);
}
