package com.sdouglass.librarybe.entity;

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
}
