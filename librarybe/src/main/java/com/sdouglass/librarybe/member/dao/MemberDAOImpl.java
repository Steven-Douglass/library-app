package com.sdouglass.librarybe.member.dao;

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

    @Autowired
    public MemberDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
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
        // Get a list of book IDS the member has checked out
        Session currentSession = entityManager.unwrap(Session.class);

        // The below commented 3 lines work
//        Query<CheckOutTransaction> checkOutTransactionQuery = currentSession.createQuery("FROM CheckOutTransaction co " +
//                "WHERE co.memberID = :id", CheckOutTransaction.class).setParameter("id", id);
//        List<CheckOutTransaction> checkOutTransactions = checkOutTransactionQuery.getResultList();

        try {
            Query<CheckOutTransaction> checkOutTransactionQuery = currentSession.createQuery(
                    "FROM CheckOutTransaction co \n" +
                            "LEFT JOIN CheckInTransaction ci ON co.checkOutTransactionID = ci.checkOutTransactionID \n" +
                            "WHERE co.memberID = :id", CheckOutTransaction.class).setParameter("id", id);
            List<CheckOutTransaction> checkOutTransactions = checkOutTransactionQuery.getResultList();
            System.out.println("Breakpoint here");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


        /*
        DELETE FROM BookAuthor ba WHERE ba.bookID=:id
         */


        List<Integer> bookIDs = new ArrayList<>();
        // Get a list of book IDs the member has checked out
//        Query<Book> bookQuery = currentSession.createQuery("FROM Book b WHERE b.memberID = :id", Book.class);
//        bookQuery.setParameter("id", id);
//        List<Book> books = bookQuery.getResultList();
//        List<Integer> bookIDs = books.stream().map(Book::getBookID).collect(Collectors.toList());

        // Remove the MemberID entry for the book to indicate it is no longer checked out by the member
        if (!bookIDs.isEmpty()) {
            Query<Book> bookUpdateQuery = currentSession.createQuery("UPDATE Book b set b.memberID = null " +
                    "WHERE id IN :bookIDs");
            bookUpdateQuery.setParameter("bookIDs", bookIDs);
            bookUpdateQuery.executeUpdate();
        }

        // Delete the member
        Query memberQuery = currentSession.createQuery("DELETE FROM Member m WHERE m.memberID = :id");
        memberQuery.setParameter("id", id);
        memberQuery.executeUpdate();
    }
}
