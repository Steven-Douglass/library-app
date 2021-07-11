package com.sdouglass.librarybe.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AuthorTest {

    @Test
    void testEquals() {
        Author author1 = new Author();
        author1.setAuthorID(3);
        author1.setFirstName("J. K.");
        author1.setLastName("Rowling");

        Author author2 = new Author();
        author2.setAuthorID(3);
        author2.setFirstName("J. K.");
        author2.setLastName("Rowling");

        Author author3 = new Author();
        author3.setAuthorID(1);
        author3.setFirstName("Ernest");
        author3.setLastName("Hemingway");

        assertEquals(author1, author1);
        assertEquals(author2, author2);
        assertEquals(author1, author2);
        assertEquals(author2, author1);
        assertNotEquals(author1, author3);
        assertNotEquals(author3, author1);
        assertNotEquals(author2, author3);
        assertNotEquals(author3, author2);
    }
}