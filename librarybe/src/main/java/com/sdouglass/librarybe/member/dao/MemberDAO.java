package com.sdouglass.librarybe.member.dao;

import com.sdouglass.librarybe.member.entity.Member;

import java.util.List;

public interface MemberDAO {
    public Member getMember(Integer id);
    public List<Member> getAllMembers();
    public void saveMember(Member member);
    public void deleteMember(Integer id);
}
