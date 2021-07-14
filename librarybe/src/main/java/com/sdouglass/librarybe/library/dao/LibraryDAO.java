package com.sdouglass.librarybe.library.dao;

import com.sdouglass.librarybe.library.entity.Library;

import java.util.List;

public interface LibraryDAO {
    public Library getLibrary(Integer id);
    public List<Library> getAllLibraries();
    public void saveLibrary(Library library);
    public void deleteLibrary(Integer id);
}
