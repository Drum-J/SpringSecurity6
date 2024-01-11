package com.springsecurity8.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter @Setter
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor
public class Accounts {

    @Id
    private long accountNumber;

    private int customerId;

    private String accountType;
    private String branchAddress;
    private String createDt;

}
