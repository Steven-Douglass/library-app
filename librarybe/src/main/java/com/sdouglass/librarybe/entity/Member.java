package com.sdouglass.librarybe.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="MemberID")
    private Integer memberID;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String memberSince;

    @Column
    private Integer addressID;

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }

        if (!(object instanceof Member)) {
            return false;
        }

        Member member = (Member) object;
        return this.getMemberID() == member.getMemberID() &&
               this.getFirstName().equals(member.getFirstName()) &&
               this.getLastName().equals(member.getLastName()) &&
               this.getMemberSince().equals(member.getMemberSince()) &&
               this.getAddressID() == member.getAddressID();
    }
}
