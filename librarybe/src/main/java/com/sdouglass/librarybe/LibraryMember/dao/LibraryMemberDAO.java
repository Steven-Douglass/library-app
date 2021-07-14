package com.sdouglass.librarybe.LibraryMember.dao;

import com.sdouglass.librarybe.LibraryMember.entity.LibraryMember;

import java.util.List;

public interface LibraryMemberDAO {
    public LibraryMember getLibraryMember(Integer id);
    public List<LibraryMember> getAllLibraryMembers();
    public void saveLibraryMember(LibraryMember libraryMember);
    public void deleteLibraryMember(Integer id);
}
