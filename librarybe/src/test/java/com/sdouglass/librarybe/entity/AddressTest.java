package com.sdouglass.librarybe.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddressTest {

    @Test
    void testEquals() {
        Address address1 = new Address();
        address1.setAddressID(6);
        address1.setAddressLine1("1200 Library Street");
        address1.setCity("Camden");
        address1.setState("NJ");
        address1.setPostalCode("08105-1234");

        Address address2 = new Address();
        address2.setAddressID(6);
        address2.setAddressLine1("1200 Library Street");
        address2.setCity("Camden");
        address2.setState("NJ");
        address2.setPostalCode("08105-1234");

        Address address3 = new Address();
        address3.setAddressID(2);
        address3.setAddressLine1("54 Central Road");
        address3.setAddressLine2("Apartment 3B");
        address3.setCity("Philadelphia");
        address3.setState("PA");
        address3.setPostalCode("19103");

        Address address4 = new Address();
        address4.setAddressID(2);
        address4.setAddressLine1("54 Central Road");
        address4.setAddressLine2("Apartment 3B");
        address4.setCity("Philadelphia");
        address4.setState("PA");
        address4.setPostalCode("19103");

        Address address5 = new Address();
        address5.setAddressID(2);
        address5.setAddressLine1("54 Central Road");
        address5.setCity("Philadelphia");
        address5.setState("PA");
        address5.setPostalCode("19103");

        assertEquals(address1, address1);
        assertEquals(address2, address2);
        assertEquals(address1, address2);
        assertEquals(address2, address1);
        assertNotEquals(address1, address3);
        assertNotEquals(address3, address1);
        assertNotEquals(address2, address3);
        assertNotEquals(address3, address2);
        assertEquals(address3, address4);
        assertNotEquals(address3, address5);
        assertNotEquals(address5, address3);
    }
}