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
        return checkOutTransactionDAO.getCheckOutTransaction(id);
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
    public List<CheckOutTransaction> getAllCheckOutTransactionsForMember(Integer id) {
        return checkOutTransactionDAO.getAllCheckOutTransactionsForMember(id);
    }

    @Override
    @Transactional
    public CheckOutTransaction getAllCheckedOutTransactionsNotReturnedForMember(Integer id) {
        return checkOutTransactionDAO.getAllCheckedOutTransactionsNotReturnedForMember(id);
    }

    @Override
    @Transactional
    public List<CheckOutTransaction> getAllCheckOutTransactionsForBook(Integer id) {
        return checkOutTransactionDAO.getAllCheckOutTransactionsForBook(id);
    }

    @Override
    @Transactional
    public CheckOutTransaction getCheckedOutTransactionNotReturnedForBook(Integer id) {
        return checkOutTransactionDAO.getCheckedOutTransactionNotReturnedForBook(id);
    }
}
