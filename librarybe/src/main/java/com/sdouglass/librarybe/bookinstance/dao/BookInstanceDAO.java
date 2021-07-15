package com.sdouglass.librarybe.bookinstance.dao;

import com.sdouglass.librarybe.bookinstance.entity.BookInstance;

import java.util.List;

public interface BookInstanceDAO {
    public BookInstance getBookInstance(Integer id);
    public List<BookInstance> getAllBookInstances();
    public void saveBookInstance(BookInstance bookInstance);
    public void deleteBookInstance(Integer id);
}
