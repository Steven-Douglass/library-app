package com.sdouglass.librarybe.LibraryMember.service;

import com.sdouglass.librarybe.LibraryMember.dao.LibraryMemberDAO;
import com.sdouglass.librarybe.LibraryMember.entity.LibraryMember;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class LibraryMemberServiceImpl implements LibraryMemberService {

    private final LibraryMemberDAO libraryMemberDAO;

    @Autowired
    public LibraryMemberServiceImpl(LibraryMemberDAO libraryMemberDAO) {
        this.libraryMemberDAO = libraryMemberDAO;
    }

    @Override
    public LibraryMember getLibraryMember(Integer id) {
        return libraryMemberDAO.getLibraryMember(id);
    }

    @Override
    public List<LibraryMember> getAllLibraryMembers() {
        return libraryMemberDAO.getAllLibraryMembers();
    }

    @Override
    public void saveLibraryMember(LibraryMember libraryMember) {
        libraryMemberDAO.saveLibraryMember(libraryMember);
    }

    @Override
    public String deleteLibraryMember(Integer id) {
        libraryMemberDAO.deleteLibraryMember(id);
        return "Deleted LibraryMember with ID: " + id;
    }
}
