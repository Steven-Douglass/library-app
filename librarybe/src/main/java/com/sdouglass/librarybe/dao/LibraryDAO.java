package com.sdouglass.librarybe.dao;

import com.sdouglass.librarybe.entity.*;
import com.sdouglass.librarybe.member.entity.Member;

import java.util.List;

public interface LibraryDAO {
    public Book getBook(Integer id);
    public List<Book> getAllBooks();
    public void saveBook(Book book);
    public void deleteBook(Integer id);

    public Library getLibrary(Integer id);
    public List<Library> getAllLibraries();
    public void saveLibrary(Library library);
    public void deleteLibrary(Integer id);
}
