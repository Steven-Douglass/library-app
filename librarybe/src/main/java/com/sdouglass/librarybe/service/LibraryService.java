package com.sdouglass.librarybe.service;

import com.sdouglass.librarybe.entity.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LibraryService {
    public Address getAddress(Integer id);
    public List<Address> getAllAddresses();
    public void saveAddress(Address address);
    public String deleteAddress(Integer id);

    public Author getAuthor(Integer id);
    public List<Author> getAllAuthors();
    public void saveAuthor(Author author);
    public String deleteAuthor(Integer id);

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