package com.sdouglass.librarybe.checkouttransaction.dao;


import com.sdouglass.librarybe.checkouttransaction.entity.CheckOutTransaction;

import java.util.List;

public interface CheckOutTransactionDAO {
    public CheckOutTransaction getCheckOutTransaction(Integer id);
    public List<CheckOutTransaction> getAllCheckOutTransactions();
    public List<CheckOutTransaction> getAllCheckOutTransactionsNotReturned();
    public void saveCheckOutTransaction(CheckOutTransaction checkOutTransaction);
    public void deleteCheckOutTransaction(Integer id);
    public Boolean isBookInstanceCheckedOut(Integer bookInstanceId);

    public List<CheckOutTransaction> getAllCheckOutTransactionsForMember(Integer id);
    public CheckOutTransaction getAllCheckedOutTransactionsNotReturnedForMember(Integer id);

    public List<CheckOutTransaction> getAllCheckOutTransactionsForBook(Integer id);
    public CheckOutTransaction getCheckedOutTransactionNotReturnedForBook(Integer id);
}


