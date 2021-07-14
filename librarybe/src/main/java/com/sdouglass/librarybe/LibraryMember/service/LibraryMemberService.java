package com.sdouglass.librarybe.LibraryMember.service;

import com.sdouglass.librarybe.LibraryMember.entity.LibraryMember;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LibraryMemberService {
    public LibraryMember getLibraryMember(Integer id);
    public List<LibraryMember> getAllLibraryMembers();
    public void saveLibraryMember(LibraryMember libraryMember);
    public String deleteLibraryMember(Integer id);
    public void deleteLibraryMemberForMemberId(Integer memberId);
}
