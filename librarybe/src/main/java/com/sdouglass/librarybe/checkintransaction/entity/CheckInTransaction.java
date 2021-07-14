package com.sdouglass.librarybe.checkintransaction.entity;

import com.sdouglass.librarybe.checkouttransaction.entity.CheckOutTransaction;
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

//    @Column
//    private Integer checkOutTransactionID;

    @Column
    private String dateCheckedIn;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "checkOutTransactionID")
    private CheckOutTransaction checkOutTransaction;

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }

        if (!(object instanceof CheckInTransaction)) {
            return false;
        }

        CheckInTransaction checkInTransaction = (CheckInTransaction) object;
        return this.getCheckInTransactionID() == checkInTransaction.getCheckInTransactionID() &&
                this.getDateCheckedIn() == checkInTransaction.getDateCheckedIn();
    }
}
