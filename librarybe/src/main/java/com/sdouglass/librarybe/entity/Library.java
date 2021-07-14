package com.sdouglass.librarybe.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sdouglass.librarybe.address.entity.Address;
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

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="AddressID")
    @JsonIgnore
    private Address address;

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
               this.getName().equals(library.getName());
    }
}
