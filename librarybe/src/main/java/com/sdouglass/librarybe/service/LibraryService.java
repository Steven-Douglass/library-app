package com.sdouglass.librarybe.service;

import com.sdouglass.librarybe.author.entity.Author;
import com.sdouglass.librarybe.entity.*;
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

    public Member getMember(Integer id);
    public List<Member> getAllMembers();
    public void saveMember(Member member);
    public String deleteMember(Integer id);
}
