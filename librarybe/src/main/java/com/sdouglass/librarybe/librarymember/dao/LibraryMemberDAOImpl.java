package com.sdouglass.librarybe.librarymember.dao;

import com.sdouglass.librarybe.librarymember.entity.LibraryMember;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class LibraryMemberDAOImpl implements LibraryMemberDAO {

    private final EntityManager entityManager;

    @Autowired
    public LibraryMemberDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public LibraryMember getLibraryMember(Integer id) {
        Session currentSession = entityManager.unwrap(Session.class);
        return currentSession.get(LibraryMember.class, id);
    }

    @Override
    public List<LibraryMember> getAllLibraryMembers() {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<LibraryMember> libraryMemberQuery = currentSession.createQuery("FROM LibraryMember", LibraryMember.class);
        List<LibraryMember> libraryMembers = libraryMemberQuery.getResultList();
        return libraryMembers;
    }

    @Override
    public void saveLibraryMember(LibraryMember libraryMember) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.saveOrUpdate(libraryMember);
    }

    @Override
    public void deleteLibraryMember(Integer id) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query addressQuery = currentSession.createQuery("DELETE FROM LibraryMember lm WHERE lm.libraryMemberID = :id");
        addressQuery.setParameter("id", id);
        addressQuery.executeUpdate();
    }

    @Override
    public void deleteLibraryMemberForMemberId(Integer memberId) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query addressQuery = currentSession.createQuery("DELETE FROM LibraryMember lm WHERE lm.memberID = :memberId");
        addressQuery.setParameter("memberId", memberId);
        addressQuery.executeUpdate();
    }
}
