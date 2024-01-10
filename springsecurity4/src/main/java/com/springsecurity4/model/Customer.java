package com.springsecurity4.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.*;
import static lombok.AccessLevel.*;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Customer {

    @Id @GeneratedValue(strategy = IDENTITY)
    private int id;

    private String email;
    private String password;
    private String role;

    public Customer(String email, String password, String role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public void setHashingPassword(String encode) {
        this.password = encode;
    }
}
