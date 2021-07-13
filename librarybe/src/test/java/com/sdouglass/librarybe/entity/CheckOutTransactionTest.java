package com.sdouglass.librarybe.entity;

import com.sdouglass.librarybe.checkouttransaction.entity.CheckOutTransaction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CheckOutTransactionTest {

    @Test
    void testEquals() {
        CheckOutTransaction checkOutTransaction1 = new CheckOutTransaction();
        checkOutTransaction1.setCheckOutTransactionID(1);
        checkOutTransaction1.setBookInstanceID(1);
        checkOutTransaction1.setMemberID(1);
        checkOutTransaction1.setDateCheckedOut("2020-06-13");

        CheckOutTransaction checkOutTransaction2 = new CheckOutTransaction();
        checkOutTransaction2.setCheckOutTransactionID(1);
        checkOutTransaction2.setBookInstanceID(1);
        checkOutTransaction2.setMemberID(1);
        checkOutTransaction2.setDateCheckedOut("2020-06-13");

        CheckOutTransaction checkOutTransaction3 = new CheckOutTransaction();
        checkOutTransaction3.setCheckOutTransactionID(2);
        checkOutTransaction3.setBookInstanceID(2);
        checkOutTransaction3.setMemberID(2);
        checkOutTransaction3.setDateCheckedOut("2014-02-07");

        assertEquals(checkOutTransaction1, checkOutTransaction1);
        assertEquals(checkOutTransaction2, checkOutTransaction2);
        assertEquals(checkOutTransaction1, checkOutTransaction2);
        assertEquals(checkOutTransaction2, checkOutTransaction1);
        assertNotEquals(checkOutTransaction1, checkOutTransaction3);
        assertNotEquals(checkOutTransaction3, checkOutTransaction1);
        assertNotEquals(checkOutTransaction2, checkOutTransaction3);
        assertNotEquals(checkOutTransaction3, checkOutTransaction2);
    }
}