package com.sdouglass.librarybe.bookinstance.service;

import com.sdouglass.librarybe.bookauthor.entity.BookAuthor;
import com.sdouglass.librarybe.bookinstance.dao.BookInstanceDAO;
import com.sdouglass.librarybe.bookinstance.entity.BookInstance;
import com.sdouglass.librarybe.checkouttransaction.entity.CheckOutTransaction;
import com.sdouglass.librarybe.checkouttransaction.service.CheckOutTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BookInstanceServiceImpl implements BookInstanceService {

    private final BookInstanceDAO bookInstanceDAO;
    private final CheckOutTransactionService checkOutTransactionService;

    @Autowired
    public BookInstanceServiceImpl(BookInstanceDAO bookInstanceDAO,
                                   CheckOutTransactionService checkOutTransactionService) {
        this.bookInstanceDAO = bookInstanceDAO;
        this.checkOutTransactionService = checkOutTransactionService;
    }

    @Override
    @Transactional
    public BookInstance getBookInstance(Integer id) {
        Optional<BookInstance> bookInstance = Optional.ofNullable(bookInstanceDAO.getBookInstance(id));
        return bookInstance.orElseThrow(() -> new RuntimeException("BookInstance ID not found: " + id));
    }

    @Override
    @Transactional
    public List<BookInstance> getAllBookInstances() {
        Optional<List<BookInstance>> bookInstances = Optional.ofNullable(bookInstanceDAO.getAllBookInstances());
        return bookInstances.orElseThrow(() -> new RuntimeException("BookInstances not found"));
    }

    @Override
    @Transactional
    public void saveBookInstance(BookInstance bookInstance) {
        try {
            bookInstanceDAO.saveBookInstance(bookInstance);
        } catch (Exception e) {
            System.out.println("Exception caught when saving BookInstance: " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public String deleteBookInstance(Integer id) {
        BookInstance bookInstance = getBookInstance(id);

        List<CheckOutTransaction> checkOutTransactions = checkOutTransactionService
                .getAllCheckOutTransactionsForBook(id);

        for (CheckOutTransaction checkOutTransaction : checkOutTransactions) {
            checkOutTransactionService.deleteCheckOutTransaction(checkOutTransaction.getCheckOutTransactionID());
        }

        bookInstanceDAO.deleteBookInstance(bookInstance.getBookInstanceID());
        return "Deleted BookInstance with ID: " + id;
    }
}
