package com.sdouglass.librarybe.LibraryMember.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class LibraryMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="LibraryMemberID")
    private Integer libraryMemberID;

    @Column
    private Integer libraryID;

    @Column
    private Integer memberID;

    @Column
    private String memberSince;

}
