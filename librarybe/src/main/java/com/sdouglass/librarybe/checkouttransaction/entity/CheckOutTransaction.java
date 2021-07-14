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

/*
    @ToDo Cascade delete isn't working. Attempting to delete a CheckOutTransaction without first deleting
    the corresponding CheckInTransaction results in a foreign key constraint failure. This has been overcome
    by the CheckOutTransactionService calling the CheckInTransactionService to delete the CheckInTransaction
    before deleting a CheckOutTransaction. Needs more investigation (Hibernate)
 */
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
