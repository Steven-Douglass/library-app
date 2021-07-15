package com.sdouglass.librarybe.bookinstance.entity;

import com.sdouglass.librarybe.bookauthor.entity.BookAuthor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class BookInstance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="BookInstanceID")
    private Integer bookInstanceID;

    @Column
    private Integer bookID;

    @Column
    private Integer libraryID;

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }

        if (!(object instanceof BookInstance)) {
            return false;
        }

        BookInstance bookInstance = (BookInstance) object;
        return this.getBookInstanceID() == bookInstance.getBookInstanceID() &&
                this.getBookID() == bookInstance.getBookID() &&
                this.getLibraryID() == bookInstance.getLibraryID();
    }
}
