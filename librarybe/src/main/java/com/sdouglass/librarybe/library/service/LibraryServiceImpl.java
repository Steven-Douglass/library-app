package com.sdouglass.librarybe.library.service;

import com.sdouglass.librarybe.address.service.AddressService;
import com.sdouglass.librarybe.bookinstance.entity.BookInstance;
import com.sdouglass.librarybe.bookinstance.service.BookInstanceService;
import com.sdouglass.librarybe.library.dao.LibraryDAO;
import com.sdouglass.librarybe.library.entity.Library;
import com.sdouglass.librarybe.librarymember.service.LibraryMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LibraryServiceImpl implements LibraryService {

    private final LibraryDAO libraryDAO;
    private final BookInstanceService bookInstanceService;
    private final LibraryMemberService libraryMemberService;
    private final AddressService addressService;

    @Autowired
    public LibraryServiceImpl(LibraryDAO libraryDAO,
                              BookInstanceService bookInstanceService,
                              LibraryMemberService libraryMemberService,
                              AddressService addressService){
        this.libraryDAO = libraryDAO;
        this.bookInstanceService = bookInstanceService;
        this.libraryMemberService = libraryMemberService;
        this.addressService = addressService;
    }

    @Override
    @Transactional
    public Library getLibrary(Integer id) {
        Library library = libraryDAO.getLibrary(id);
        if (library == null) {
            throw new RuntimeException("Library ID not found: " + id);
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

        List<BookInstance> bookInstances = bookInstanceService.getAllBookInstancesForLibrary(id);
        for (BookInstance bookInstance : bookInstances) {
            bookInstanceService.deleteBookInstance(bookInstance.getBookInstanceID());
        }

        libraryMemberService.deleteLibraryMemberForLibraryId(id);
        libraryDAO.deleteLibrary(library.getLibraryID());
        addressService.deleteAddress(library.getAddress().getAddressID());
        return "Deleted Library with ID: " + id;
    }
}
