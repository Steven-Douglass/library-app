package com.sdouglass.librarybe.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookTest {

    @Test
    void testEquals() {
        Book book1 = new Book();
        book1.setBookID(1);
        book1.setTitle("David Copperfield");
        book1.setPublishDate("1849-05-01");
        book1.setLibraryID(1);
        book1.setMemberID(null);

        Book book2 = new Book();
        book2.setBookID(1);
        book2.setTitle("David Copperfield");
        book2.setPublishDate("1849-05-01");
        book2.setLibraryID(1);
        book2.setMemberID(null);

        Book book3 = new Book();
        book3.setBookID(6);
        book3.setTitle("Harry Potter and the Sorcerers Stone");
        book3.setPublishDate("1997-06-26");
        book3.setLibraryID(1);
        book3.setMemberID(null);

        assertEquals(book1, book1);
        assertEquals(book2, book2);
        assertEquals(book1, book2);
        assertEquals(book2, book1);
        assertNotEquals(book1, book3);
        assertNotEquals(book3, book1);
        assertNotEquals(book2, book3);
        assertNotEquals(book3, book2);
    }
}