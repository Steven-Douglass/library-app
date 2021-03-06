package com.sdouglass.librarybe.checkouttransaction.service;

import com.sdouglass.librarybe.checkintransaction.service.CheckInTransactionService;
import com.sdouglass.librarybe.checkouttransaction.dao.CheckOutTransactionDAO;
import com.sdouglass.librarybe.checkouttransaction.entity.CheckOutTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CheckOutTransactionServiceImpl implements CheckOutTransactionService {

    private final CheckOutTransactionDAO checkOutTransactionDAO;
    private final CheckInTransactionService checkInTransactionService;

    @Autowired
    public CheckOutTransactionServiceImpl(CheckOutTransactionDAO checkOutTransactionDAO,
                                          CheckInTransactionService checkInTransactionService) {
        this.checkOutTransactionDAO = checkOutTransactionDAO;
        this.checkInTransactionService = checkInTransactionService;
    }

    @Override
    @Transactional
    public CheckOutTransaction getCheckOutTransaction(Integer id) {
        CheckOutTransaction checkOutTransaction = checkOutTransactionDAO.getCheckOutTransaction(id);
        if (checkOutTransaction == null) {
            throw new RuntimeException("CheckOutTransaction ID not found: " + id);
        }
        return checkOutTransaction;
    }

    @Override
    @Transactional
    public List<CheckOutTransaction> getAllCheckOutTransactions() {
        return checkOutTransactionDAO.getAllCheckOutTransactions();
    }

    @Override
    @Transactional
    public List<CheckOutTransaction> getAllCheckOutTransactionsNotReturned() {
        return checkOutTransactionDAO.getAllCheckOutTransactionsNotReturned();
    }

    @Override
    @Transactional
    public void saveCheckOutTransaction(CheckOutTransaction checkOutTransaction) {
        checkOutTransactionDAO.saveCheckOutTransaction(checkOutTransaction);
    }

    @Override
    @Transactional
    public void deleteCheckOutTransaction(Integer id) {
        // Check if there is a corresponding CheckInTransaction
        CheckOutTransaction checkOutTransaction = checkOutTransactionDAO.getCheckOutTransaction(id);
        if (checkOutTransaction.getCheckInTransaction() != null) {
            checkInTransactionService.deleteCheckInTransaction(checkOutTransaction
                    .getCheckInTransaction()
                    .getCheckInTransactionID());
        }

        // Delete the CheckOutTransaction
        checkOutTransactionDAO.deleteCheckOutTransaction(id);
    }

    @Override
    @Transactional
    public Boolean isBookInstanceCheckedOut(Integer bookInstanceId) {
        return checkOutTransactionDAO.isBookInstanceCheckedOut(bookInstanceId);
    }

    @Override
    @Transactional
    public List<CheckOutTransaction> getAllCheckOutTransactionsForMember(Integer memberId) {
        return checkOutTransactionDAO.getAllCheckOutTransactionsForMember(memberId);
    }

    @Override
    @Transactional
    public List<CheckOutTransaction> getAllCheckedOutTransactionsNotReturnedForMember(Integer memberId) {
        return checkOutTransactionDAO.getAllCheckedOutTransactionsNotReturnedForMember(memberId);
    }

    @Override
    @Transactional
    public void deleteAllCheckOutTransactionsForMember(Integer memberId) {
        List<CheckOutTransaction> getAllCheckOutTransactionsForMember =
                this.getAllCheckOutTransactionsForMember(memberId);

        for (CheckOutTransaction checkOutTransaction : getAllCheckOutTransactionsForMember) {
            deleteCheckOutTransaction(checkOutTransaction.getCheckOutTransactionID());
        }
    }

    @Override
    @Transactional
    public List<CheckOutTransaction> getAllCheckOutTransactionsForBook(Integer bookInstanceId) {
        return checkOutTransactionDAO.getAllCheckOutTransactionsForBook(bookInstanceId);
    }

    @Override
    @Transactional
    public CheckOutTransaction getCheckedOutTransactionNotReturnedForBook(Integer bookInstanceId) {
        return checkOutTransactionDAO.getCheckedOutTransactionNotReturnedForBook(bookInstanceId);
    }
}
