package com.sdouglass.librarybe.checkouttransaction.entity;

import com.sdouglass.librarybe.checkintransaction.entity.CheckInTransaction;
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

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }

        if (!(object instanceof CheckOutTransaction)) {
            return false;
        }

        CheckOutTransaction checkOutTransaction = (CheckOutTransaction) object;
        return this.getCheckOutTransactionID() == checkOutTransaction.getCheckOutTransactionID() &&
                this.getBookInstanceID() == checkOutTransaction.getBookInstanceID() &&
                this.getMemberID() == checkOutTransaction.getMemberID() &&
                this.getDateCheckedOut().equals(checkOutTransaction.getDateCheckedOut());
    }

}
