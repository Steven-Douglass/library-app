package com.sdouglass.librarybe.checkouttransaction.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class CheckOutTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="CheckOutTransactionID")
    private Integer checkOutTransactionID;

    @Column
    private Integer bookInstanceID;

    @Column
    private Integer memberID;

    @Column
    private String dateCheckedOut;

    @OneToOne(mappedBy = "checkOutTransaction", cascade = CascadeType.ALL)
    private CheckInTransaction checkInTransaction;

}
