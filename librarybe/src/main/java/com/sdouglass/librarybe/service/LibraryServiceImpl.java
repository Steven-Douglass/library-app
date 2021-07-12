package com.sdouglass.librarybe.service;

import com.sdouglass.librarybe.author.entity.Author;
import com.sdouglass.librarybe.dao.LibraryDAO;
import com.sdouglass.librarybe.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LibraryServiceImpl implements LibraryService {
    private final LibraryDAO libraryDAO;

    @Autowired
    public LibraryServiceImpl(LibraryDAO libraryDAO){
        this.libraryDAO = libraryDAO;
    }

    @Override
    @Transactional
    public Book getBook(Integer id) {
        Book book = libraryDAO.getBook(id);
        if (book == null) {
            throw new RuntimeException("Book ID not found - " + id);
        }
        return book;
    }

    @Override
    @Transactional
    public List<Book> getAllBooks() {
        return libraryDAO.getAllBooks();
    }

    @Override
    @Transactional
    public void saveBook(Book book) {
        try {
            libraryDAO.saveBook(book);
        } catch (Exception e) {
            System.out.println("Exception caught when saving book: " + e);
        }
    }

    @Override
    @Transactional
    public String deleteBook(Integer id) {
        Book book = getBook(id);
        libraryDAO.deleteBook(book.getBookID());
        return "Deleted book with ID: " + id;
    }

    @Override
    @Transactional
    public Library getLibrary(Integer id) {
        Library library = libraryDAO.getLibrary(id);
        if (library == null) {
            throw new RuntimeException("Library ID not found - " + id);
        }
        return library;
    }

    @Override
    @Transactional
    public List<Library> getAllLibraries() {
        return libraryDAO.getAllLibraries();
    }

    @Override
    @Transactional
    public void saveLibrary(Library library) {
        libraryDAO.saveLibrary(library);
    }

    @Override
    @Transactional
    public String deleteLibrary(Integer id) {
        Library library = getLibrary(id);
        libraryDAO.deleteLibrary(library.getLibraryID());
        return "Deleted Library with ID: " + id;
    }

    @Override
    @Transactional
    public Member getMember(Integer id) {
        Member member = libraryDAO.getMember(id);
        if (member == null) {
            throw new RuntimeException("Member ID not found - " + id);
        }
        return member;
    }

    @Override
    @Transactional
    public List<Member> getAllMembers() {
        return libraryDAO.getAllMembers();
    }

    @Override
    @Transactional
    public void saveMember(Member member) {
        libraryDAO.saveMember(member);
    }

    @Override
    @Transactional
    public String deleteMember(Integer id) {
        Member member = getMember(id);
        libraryDAO.deleteMember(member.getMemberID());
        return "Deleted Member with ID: " + id;
    }
}
