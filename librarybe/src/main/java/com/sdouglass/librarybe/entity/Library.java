package com.sdouglass.librarybe.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Library {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="LibraryID")
    private Integer libraryID;

    @Column
    private String name;

    @Column
    private Integer addressID;

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }

        if (!(object instanceof Library)) {
            return false;
        }

        Library library = (Library) object;
        return this.getLibraryID() == library.getLibraryID() &&
               this.getName().equals(library.getName()) &&
               this.getAddressID() == library.getAddressID();
    }
}
