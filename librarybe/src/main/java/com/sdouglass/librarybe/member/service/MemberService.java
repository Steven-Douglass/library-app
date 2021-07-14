package com.sdouglass.librarybe.member.service;

import com.sdouglass.librarybe.member.entity.Member;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MemberService {
    public Member getMember(Integer id);
    public List<Member> getAllMembers();
    public void saveMember(Member member);
    public String deleteMember(Integer id);
}
