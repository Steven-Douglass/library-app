package com.sdouglass.librarybe.checkintransaction.dao;

import com.sdouglass.librarybe.checkintransaction.entity.CheckInTransaction;

import java.util.List;

public interface CheckInTransactionDAO {
    public CheckInTransaction getCheckInTransaction(Integer id);
    public List<CheckInTransaction> getAllCheckInTransactions();
    public void saveCheckInTransaction(CheckInTransaction checkInTransaction);
    public void deleteCheckInTransaction(Integer id);
}
