package com.springsecurity7.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "authorities")
@NoArgsConstructor
@Getter @Setter
public class Authority {

    @Id @GeneratedValue(strategy = IDENTITY)
    public Long id;

    private String name;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

}
