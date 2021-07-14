package com.sdouglass.librarybe.library.service;

import com.sdouglass.librarybe.library.entity.Library;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LibraryService {
    public Library getLibrary(Integer id);
    public List<Library> getAllLibraries();
    public void saveLibrary(Library library);
    public String deleteLibrary(Integer id);
}
