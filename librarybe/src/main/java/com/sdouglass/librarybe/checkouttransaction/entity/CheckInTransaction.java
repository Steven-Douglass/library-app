package com.sdouglass.librarybe.checkouttransaction.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class CheckInTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="CheckInTransactionID")
    private Integer checkInTransactionID;

    @Column
    private Integer checkOutTransactionID;

    @Column
    private String dateCheckedIn;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "CheckOutTransactionID")
    private CheckOutTransaction checkOutTransaction;
}
