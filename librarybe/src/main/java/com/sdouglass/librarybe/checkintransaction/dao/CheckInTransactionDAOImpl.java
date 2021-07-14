package com.sdouglass.librarybe.checkintransaction.dao;

import com.sdouglass.librarybe.checkintransaction.entity.CheckInTransaction;
import com.sdouglass.librarybe.checkouttransaction.entity.CheckOutTransaction;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class CheckInTransactionDAOImpl implements CheckInTransactionDAO {

    private final EntityManager entityManager;

    @Autowired
    public CheckInTransactionDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public CheckInTransaction getCheckInTransaction(Integer id) {
        Session currentSession = entityManager.unwrap(Session.class);
        return currentSession.get(CheckInTransaction.class, id);
    }

    @Override
    public List<CheckInTransaction> getAllCheckInTransactions() {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<CheckInTransaction> checkInTransactionQuery = currentSession.createQuery("FROM CheckInTransaction", CheckInTransaction.class);
        List<CheckInTransaction> checkInTransactions = checkInTransactionQuery.getResultList();
        return checkInTransactions;
    }

    @Override
    public void saveCheckInTransaction(CheckInTransaction checkInTransaction) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.saveOrUpdate(checkInTransaction);
    }

    @Override
    public void deleteCheckInTransaction(Integer id) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query deleteQuery = currentSession.createQuery("DELETE FROM CheckInTransaction ci WHERE ci.checkInTransactionID = :id");
        deleteQuery.setParameter("id", id);
        deleteQuery.executeUpdate();
    }
}
