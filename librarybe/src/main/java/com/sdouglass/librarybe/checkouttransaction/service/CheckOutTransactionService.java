package com.sdouglass.librarybe.checkouttransaction.service;

import com.sdouglass.librarybe.checkouttransaction.entity.CheckOutTransaction;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CheckOutTransactionService {
    public CheckOutTransaction getCheckOutTransaction(Integer id);
    public List<CheckOutTransaction> getAllCheckOutTransactions();
    public List<CheckOutTransaction> getAllCheckOutTransactionsNotReturned();
    public void saveCheckOutTransaction(CheckOutTransaction checkOutTransaction);
    public void deleteCheckOutTransaction(Integer id);
    public Boolean isBookInstanceCheckedOut(Integer id);

    public List<CheckOutTransaction> getAllCheckOutTransactionsForMember(Integer id);
    public CheckOutTransaction getAllCheckedOutTransactionsNotReturnedForMember(Integer id);

    public List<CheckOutTransaction> getAllCheckOutTransactionsForBook(Integer id);
    public CheckOutTransaction getCheckedOutTransactionNotReturnedForBook(Integer id);
}
