package com.springsecurity7.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static jakarta.persistence.GenerationType.*;
import static lombok.AccessLevel.*;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Customer {

    @Id @GeneratedValue(strategy = IDENTITY)
    @Column(name = "customer_id")
    private int id;

    private String email;
    private String password;
    private String role;

    private String name;
    private String mobileNumber;

    @Setter
    private String createDt;

    public Customer(String email, String password, String role, String mobileNumber, String name) {
        this.email = email;
        this.password = password;
        this.role = role;
        this.mobileNumber = mobileNumber;
        this.name = name;
    }

    public void setHashingPassword(String encode) {
        this.password = encode;
    }
}
