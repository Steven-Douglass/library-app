package com.sdouglass.librarybe.dao;

import com.sdouglass.librarybe.author.entity.Author;
import com.sdouglass.librarybe.entity.*;

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

    public Member getMember(Integer id);
    public List<Member> getAllMembers();
    public void saveMember(Member member);
    public void deleteMember(Integer id);
}
