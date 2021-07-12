package com.sdouglass.librarybe.author.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="AuthorID")
    private Integer authorID;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }

        if (!(object instanceof Author)) {
            return false;
        }

        Author author = (Author) object;
        return this.getAuthorID() == author.getAuthorID() &&
               this.getFirstName().equals(author.getFirstName()) &&
               this.getLastName().equals(author.getLastName());
    }
}
