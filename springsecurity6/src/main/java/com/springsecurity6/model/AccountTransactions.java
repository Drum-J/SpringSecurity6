package com.springsecurity6.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.sql.Date;

@Entity
@Getter @Setter
@NoArgsConstructor
public class AccountTransactions {

    @Id
    private String transactionId;

    private long accountNumber;
    private int customerId;
    private Date transactionDt;
    private String transactionsSummary;
    private String transactionType;
    private int transactionAmt;
    private int closingBalance;
    private String createDt;

}
