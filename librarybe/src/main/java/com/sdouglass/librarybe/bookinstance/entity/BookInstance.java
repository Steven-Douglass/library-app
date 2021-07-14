package com.sdouglass.librarybe.bookinstance.entity;

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
}
