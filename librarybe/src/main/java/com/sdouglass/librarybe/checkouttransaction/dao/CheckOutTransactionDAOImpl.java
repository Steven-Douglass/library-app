package com.sdouglass.librarybe.checkouttransaction.dao;

import com.sdouglass.librarybe.checkouttransaction.entity.CheckOutTransaction;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
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

    }

    @Override
    public Boolean isBookInstanceCheckedOut(Integer id) {
        return null;
    }

    @Override
    public List<CheckOutTransaction> getAllCheckOutTransactionsForMember(Integer id) {
        return null;
    }

    @Override
    public CheckOutTransaction getAllCheckedOutTransactionsNotReturnedForMember(Integer id) {
        return null;
    }

    @Override
    public List<CheckOutTransaction> getAllCheckOutTransactionsForBook(Integer id) {
        return null;
    }

    @Override
    public CheckOutTransaction getCheckedOutTransactionNotReturnedForBook(Integer id) {
        return null;
    }
}
