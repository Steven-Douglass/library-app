package com.sdouglass.librarybe.entity;

import com.sdouglass.librarybe.checkintransaction.entity.CheckInTransaction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CheckInTransactionTest {

    @Test
    void testEquals() {
        CheckInTransaction checkInTransaction1 = new CheckInTransaction();
        checkInTransaction1.setCheckInTransactionID(1);
        checkInTransaction1.setDateCheckedIn("2021-03-05");

        CheckInTransaction checkInTransaction2 = new CheckInTransaction();
        checkInTransaction2.setCheckInTransactionID(1);
        checkInTransaction2.setDateCheckedIn("2021-03-05");

        CheckInTransaction checkInTransaction3 = new CheckInTransaction();
        checkInTransaction3.setCheckInTransactionID(2);
        checkInTransaction3.setDateCheckedIn("2021-07-14");

        assertEquals(checkInTransaction1, checkInTransaction1);
        assertEquals(checkInTransaction2, checkInTransaction2);
        assertEquals(checkInTransaction1, checkInTransaction2);
        assertEquals(checkInTransaction2, checkInTransaction1);
        assertNotEquals(checkInTransaction1, checkInTransaction3);
        assertNotEquals(checkInTransaction3, checkInTransaction1);
        assertNotEquals(checkInTransaction2, checkInTransaction3);
        assertNotEquals(checkInTransaction3, checkInTransaction2);
    }
}