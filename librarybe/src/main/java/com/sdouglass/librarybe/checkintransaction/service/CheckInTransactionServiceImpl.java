package com.sdouglass.librarybe.checkintransaction.service;

import com.sdouglass.librarybe.checkintransaction.dao.CheckInTransactionDAO;
import com.sdouglass.librarybe.checkintransaction.entity.CheckInTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CheckInTransactionServiceImpl implements CheckInTransactionService {

    private final CheckInTransactionDAO checkInTransactionDAO;

    @Autowired
    public CheckInTransactionServiceImpl(CheckInTransactionDAO checkInTransactionDAO) {
        this.checkInTransactionDAO = checkInTransactionDAO;
    }


    @Override
    @Transactional
    public CheckInTransaction getCheckInTransaction(Integer id) {
        return checkInTransactionDAO.getCheckInTransaction(id);
    }

    @Override
    @Transactional
    public List<CheckInTransaction> getAllCheckInTransactions() {
        return checkInTransactionDAO.getAllCheckInTransactions();
    }

    @Override
    @Transactional
    public void saveCheckInTransaction(CheckInTransaction checkInTransaction) {
        checkInTransactionDAO.saveCheckInTransaction(checkInTransaction);
    }

    @Override
    @Transactional
    public void deleteCheckInTransaction(Integer id) {
        checkInTransactionDAO.deleteCheckInTransaction(id);
    }
}
