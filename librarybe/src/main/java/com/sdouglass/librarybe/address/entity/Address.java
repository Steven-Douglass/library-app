package com.sdouglass.librarybe.address.entity;

import com.sdouglass.librarybe.entity.Library;
import com.sdouglass.librarybe.member.entity.Member;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="AddressID")
    private Integer addressID;

    @Column
    private String addressLine1;

    @Column
    private String addressLine2;

    @Column
    private String city;

    @Column
    private String state;

    @Column
    private String postalCode;

    @OneToOne(mappedBy = "address", fetch = FetchType.LAZY)
    private Member member;

    @OneToOne(mappedBy = "address")
    private Library library;

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }

        if (!(object instanceof Address)) {
            return false;
        }

        Address address = (Address) object;
        if (address.getAddressLine2() == null && this.getAddressLine2() == null) {
            return this.getAddressID() == address.getAddressID() &&
                   this.getAddressLine1().equals(address.getAddressLine1()) &&
                   this.getCity().equals(address.getCity()) &&
                   this.getState().equals(address.getState()) &&
                   this.getPostalCode().equals(address.getPostalCode());
        } else if (!(address.getAddressLine2() == null) && !(this.getAddressLine2() == null)) {
            return this.getAddressID() == address.getAddressID() &&
                   this.getAddressLine1().equals(address.getAddressLine1()) &&
                   this.getAddressLine2().equals(address.getAddressLine2()) &&
                   this.getCity().equals(address.getCity()) &&
                   this.getState().equals(address.getState()) &&
                   this.getPostalCode().equals(address.getPostalCode());
        } else {
            return false;
        }
    }
}
