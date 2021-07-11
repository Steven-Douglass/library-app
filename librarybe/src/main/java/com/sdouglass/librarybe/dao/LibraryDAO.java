package com.sdouglass.librarybe.dao;

import com.sdouglass.librarybe.entity.*;

import java.util.List;

public interface LibraryDAO {
    public Address getAddress(Integer id);
    public List<Address> getAllAddresses();
    public void saveAddress(Address address);
    public void deleteAddress(Integer id);

    public Author getAuthor(Integer id);
    public List<Author> getAllAuthors();
    public void saveAuthor(Author author);
    public void deleteAuthor(Integer id);

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
