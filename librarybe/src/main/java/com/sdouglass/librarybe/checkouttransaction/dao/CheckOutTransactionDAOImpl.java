package com.sdouglass.librarybe.checkouttransaction.dao;

import com.sdouglass.librarybe.checkouttransaction.entity.CheckOutTransaction;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Repository
public class CheckOutTransactionDAOImpl implements CheckOutTransactionDAO {

    private final EntityManager entityManager;

    @Autowired
    public CheckOutTransactionDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public CheckOutTransaction getCheckOutTransaction(Integer id) {
        Session currentSession = entityManager.unwrap(Session.class);
        return currentSession.get(CheckOutTransaction.class, id);
    }

    @Override
    public List<CheckOutTransaction> getAllCheckOutTransactions() {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<CheckOutTransaction> checkOutTransactionQuery = currentSession.createQuery("FROM CheckOutTransaction", CheckOutTransaction.class);
        List<CheckOutTransaction> checkOutTransactions = checkOutTransactionQuery.getResultList();
        return checkOutTransactions;
    }

    @Override
    public List<CheckOutTransaction> getAllCheckOutTransactionsNotReturned() {
        List<CheckOutTransaction> checkOutTransactionList = getAllCheckOutTransactions();
        Predicate<CheckOutTransaction> bookIsNotReturned = checkOutTransaction -> checkOutTransaction.getCheckInTransaction() == null;
        return checkOutTransactionList.stream().filter(bookIsNotReturned).collect(Collectors.toList());
    }

    @Override
    public void saveCheckOutTransaction(CheckOutTransaction checkOutTransaction) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.saveOrUpdate(checkOutTransaction);
    }

    @Override
    public void deleteCheckOutTransaction(Integer id) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query deleteQuery = currentSession.createQuery("DELETE FROM CheckOutTransaction co WHERE co.checkOutTransactionID = :id");
        deleteQuery.setParameter("id", id);
        deleteQuery.executeUpdate();
    }

    @Override
    public Boolean isBookInstanceCheckedOut(Integer bookInstanceId) {
        List<CheckOutTransaction> checkOutTransactions = getAllCheckOutTransactionsForBook(bookInstanceId);

        for (CheckOutTransaction checkOutTransaction : checkOutTransactions) {
            if (checkOutTransaction.getCheckInTransaction() == null) {
                return true;
            }
        }

        return false;
    }

    @Override
    public List<CheckOutTransaction> getAllCheckOutTransactionsForMember(Integer memberId) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query checkOutTransactionQuery = currentSession.createQuery("FROM CheckOutTransaction co " +
                "WHERE co.memberID = :memberId")
                .setParameter("memberId", memberId);
        List<CheckOutTransaction> checkOutTransactions = checkOutTransactionQuery.getResultList();
        return checkOutTransactions;
    }

    @Override
    public List<CheckOutTransaction> getAllCheckedOutTransactionsNotReturnedForMember(Integer memberId) {
        List<CheckOutTransaction> transactionsWithBooksNotReturned = new ArrayList<>();
        List<CheckOutTransaction> checkOutTransactions = getAllCheckOutTransactionsForMember(memberId);

        for (CheckOutTransaction checkOutTransaction : checkOutTransactions) {
            if (checkOutTransaction.getCheckInTransaction() == null) {
                transactionsWithBooksNotReturned.add(checkOutTransaction);
            }
        }

        return transactionsWithBooksNotReturned;
    }

    @Override
    public List<CheckOutTransaction> getAllCheckOutTransactionsForBook(Integer bookInstanceId) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query checkOutTransactionQuery = currentSession.createQuery("FROM CheckOutTransaction co " +
                "WHERE co.bookInstanceID = :bookInstanceId")
                .setParameter("bookInstanceId", bookInstanceId);
        List<CheckOutTransaction> checkOutTransactions = checkOutTransactionQuery.getResultList();
        return checkOutTransactions;
    }

    @Override
    public CheckOutTransaction getCheckedOutTransactionNotReturnedForBook(Integer bookInstanceId) {
        if (isBookInstanceCheckedOut(bookInstanceId)) {
            List<CheckOutTransaction> checkOutTransactions = getAllCheckOutTransactionsForBook(bookInstanceId);
            for (CheckOutTransaction checkOutTransaction : checkOutTransactions) {
                if (checkOutTransaction.getCheckInTransaction() == null) {
                    return checkOutTransaction;
                }
            }
        }
        return null;
    }
}
