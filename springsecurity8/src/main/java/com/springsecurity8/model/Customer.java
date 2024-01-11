package com.springsecurity8.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

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

    @JsonIgnore
    @OneToMany(mappedBy = "customer",fetch = FetchType.EAGER)
    public Set<Authority> authorities = new HashSet<>();

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

    public void setAuthorities(Authority authority) {
        authority.setCustomer(this);
        this.authorities.add(authority);
    }
}
