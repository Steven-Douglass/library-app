package com.sdouglass.librarybe.librarymember.service;

import com.sdouglass.librarybe.librarymember.dao.LibraryMemberDAO;
import com.sdouglass.librarybe.librarymember.entity.LibraryMember;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LibraryMemberServiceImpl implements LibraryMemberService {

    private final LibraryMemberDAO libraryMemberDAO;

    @Autowired
    public LibraryMemberServiceImpl(LibraryMemberDAO libraryMemberDAO) {
        this.libraryMemberDAO = libraryMemberDAO;
    }

    @Override
    @Transactional
    public LibraryMember getLibraryMember(Integer id) {
        LibraryMember libraryMember = libraryMemberDAO.getLibraryMember(id);
        if (libraryMember == null) {
            throw new RuntimeException("LibraryMember ID not found - " + id);
        }
        return libraryMember;
    }

    @Override
    @Transactional
    public List<LibraryMember> getAllLibraryMembers() {
        return libraryMemberDAO.getAllLibraryMembers();
    }

    @Override
    @Transactional
    public void saveLibraryMember(LibraryMember libraryMember) {
        libraryMemberDAO.saveLibraryMember(libraryMember);
    }

    @Override
    @Transactional
    public String deleteLibraryMember(Integer id) {
        libraryMemberDAO.deleteLibraryMember(id);
        return "Deleted LibraryMember with ID: " + id;
    }

    @Override
    @Transactional
    public void deleteLibraryMemberForMemberId(Integer memberId) {
        libraryMemberDAO.deleteLibraryMemberForMemberId(memberId);
    }
}
