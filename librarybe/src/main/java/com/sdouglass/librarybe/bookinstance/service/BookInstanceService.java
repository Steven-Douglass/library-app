package com.sdouglass.librarybe.bookinstance.service;

import com.sdouglass.librarybe.bookinstance.entity.BookInstance;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookInstanceService {
    public BookInstance getBookInstance(Integer id);
    public List<BookInstance> getAllBookInstances();
    public void saveBookInstance(BookInstance bookInstance);
    public String deleteBookInstance(Integer id);
    public List<BookInstance> getAllBookInstancesForLibrary(Integer libraryId);
}
