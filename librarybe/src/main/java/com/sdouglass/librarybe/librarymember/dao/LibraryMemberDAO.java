package com.sdouglass.librarybe.librarymember.dao;

import com.sdouglass.librarybe.librarymember.entity.LibraryMember;

import java.util.List;

public interface LibraryMemberDAO {
    public LibraryMember getLibraryMember(Integer id);
    public List<LibraryMember> getAllLibraryMembers();
    public void saveLibraryMember(LibraryMember libraryMember);
    public void deleteLibraryMember(Integer id);
    public void deleteLibraryMemberForLibraryId(Integer libraryId);
    public void deleteLibraryMemberForMemberId(Integer memberId);
}
