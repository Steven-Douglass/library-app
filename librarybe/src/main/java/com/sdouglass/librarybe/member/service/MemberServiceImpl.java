package com.sdouglass.librarybe.member.service;

import com.sdouglass.librarybe.member.dao.MemberDAO;
import com.sdouglass.librarybe.member.dao.MemberDAOImpl;
import com.sdouglass.librarybe.member.entity.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {

    private final MemberDAO memberDAO;

    @Autowired
    public MemberServiceImpl(MemberDAO memberDAO) {
        this.memberDAO = memberDAO;
    }

    @Override
    @Transactional
    public Member getMember(Integer id) {
        Member member = memberDAO.getMember(id);
        if (member == null) {
            throw new RuntimeException("Member ID not found - " + id);
        }
        return member;
    }

    @Override
    @Transactional
    public List<Member> getAllMembers() {
        return memberDAO.getAllMembers();
    }

    @Override
    @Transactional
    public void saveMember(Member member) {
        memberDAO.saveMember(member);
    }

    @Override
    @Transactional
    public String deleteMember(Integer id) {
        Member member = getMember(id);
        memberDAO.deleteMember(member.getMemberID());
        return "Deleted Member with ID: " + id;
    }
}
