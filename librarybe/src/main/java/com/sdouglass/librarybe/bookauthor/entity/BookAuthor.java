package com.sdouglass.librarybe.bookauthor.entity;

import com.sdouglass.librarybe.author.entity.Author;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class BookAuthor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="BookAuthorID")
    private Integer bookAuthorID;

    @Column
    private Integer bookID;

    @Column
    private Integer authorID;

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }

        if (!(object instanceof BookAuthor)) {
            return false;
        }

        BookAuthor bookAuthor = (BookAuthor) object;
        return this.getBookAuthorID() == bookAuthor.getBookAuthorID() &&
               this.getBookID() == bookAuthor.getBookID() &&
               this.getAuthorID() == bookAuthor.getAuthorID();
    }
}
