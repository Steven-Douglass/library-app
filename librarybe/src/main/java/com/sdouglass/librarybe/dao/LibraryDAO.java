package com.sdouglass.librarybe.dao;

import com.sdouglass.librarybe.book.entity.Book;
import com.sdouglass.librarybe.entity.*;

import java.util.List;

public interface LibraryDAO {
    public Library getLibrary(Integer id);
    public List<Library> getAllLibraries();
    public void saveLibrary(Library library);
    public void deleteLibrary(Integer id);
}
