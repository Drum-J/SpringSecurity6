package com.springsecurity6.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Table(name = "contact_messages")
@Getter @Setter
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor
public class Contact {

    @Id
    private String contactId;

    private String contactName;
    private String contactEmail;
    private String subject;
    private String message;
    private Date createDt;

}
