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
    public Boolean isBookInstanceCheckedOut(Integer bookInstanceId);

    public List<CheckOutTransaction> getAllCheckOutTransactionsForMember(Integer memberId);
    public List<CheckOutTransaction> getAllCheckedOutTransactionsNotReturnedForMember(Integer memberId);

    public List<CheckOutTransaction> getAllCheckOutTransactionsForBook(Integer bookInstanceId);
    public CheckOutTransaction getCheckedOutTransactionNotReturnedForBook(Integer bookInstanceId);
}
