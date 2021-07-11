package com.sdouglass.librarybe.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="BookID")
    private Integer bookID;

    @Column
    private String title;

    @Column
    private String publishDate;

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }

        if (!(object instanceof Book)) {
            return false;
        }

        Book book = (Book) object;
        return this.getBookID() == book.getBookID() &&
               this.getTitle().equals(book.getTitle()) &&
               this.getPublishDate().equals(book.getPublishDate());
    }
}
