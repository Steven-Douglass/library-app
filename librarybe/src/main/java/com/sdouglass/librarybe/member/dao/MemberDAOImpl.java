package com.sdouglass.librarybe.member.dao;

import com.sdouglass.librarybe.LibraryMember.service.LibraryMemberService;
import com.sdouglass.librarybe.checkouttransaction.entity.CheckOutTransaction;
import com.sdouglass.librarybe.entity.Book;
import com.sdouglass.librarybe.member.entity.Member;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MemberDAOImpl implements MemberDAO {

    private final EntityManager entityManager;
    private final LibraryMemberService libraryMemberService;

    @Autowired
    public MemberDAOImpl(EntityManager entityManager,
                         LibraryMemberService libraryMemberService) {
        this.entityManager = entityManager;
        this.libraryMemberService = libraryMemberService;
    }

    @Override
    public Member getMember(Integer id) {
        Session currentSession = entityManager.unwrap(Session.class);
        return currentSession.get(Member.class, id);
    }

    @Override
    public List<Member> getAllMembers() {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Member> memberQuery = currentSession.createQuery("FROM Member", Member.class);
        List<Member> members = memberQuery.getResultList();
        return members;
    }

    @Override
    public void saveMember(Member member) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.saveOrUpdate(member);
    }

    @Override
    public void deleteMember(Integer id) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query memberQuery = currentSession.createQuery("DELETE FROM Member m WHERE m.memberID = :id");
        memberQuery.setParameter("id", id);
        memberQuery.executeUpdate();
    }
}
