package com.sdouglass.librarybe.library.service;

import com.sdouglass.librarybe.library.dao.LibraryDAO;
import com.sdouglass.librarybe.library.entity.Library;
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
        // ToDO deleting a library will require deleting tables in the following order
        /*
        DROP TABLE IF EXISTS library.checkintransaction;
        We will need to get all the checkInTransactions for a specific library
        Start with library id -> get all bookinstanceids -> get all CheckOutTransaction ids
        DROP TABLE IF EXISTS library.checkouttransaction;
        DROP TABLE IF EXISTS library.bookinstance;
        DROP TABLE IF EXISTS library.librarymember;
        DROP TABLE IF EXISTS library.library;
        DROP TABLE IF EXISTS library.address;
         */

        Library library = getLibrary(id);
        libraryDAO.deleteLibrary(library.getLibraryID());
        return "Deleted Library with ID: " + id;
    }
}
