package com.sdouglass.librarybe.entity;

import com.sdouglass.librarybe.library.entity.Library;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LibraryTest {

    @Test
    void testEquals() {
        Library library1 = new Library();
        library1.setLibraryID(1);
        library1.setName("Greater Learning Library");

        Library library2 = new Library();
        library2.setLibraryID(1);
        library2.setName("Greater Learning Library");

        Library library3 = new Library();
        library3.setLibraryID(2);
        library3.setName("New Day Library");

        assertEquals(library1, library1);
        assertEquals(library2, library2);
        assertEquals(library1, library2);
        assertEquals(library2, library1);
        assertNotEquals(library1, library3);
        assertNotEquals(library3, library1);
        assertNotEquals(library2, library3);
        assertNotEquals(library3, library2);
    }
}