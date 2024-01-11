package com.springsecurity8.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Notice {

    @Id @GeneratedValue(strategy = IDENTITY)
    private int noticeId;

    private String noticeSummary;
    private String noticeDetails;
    private Date noticeBegDt;
    private Date noticeEndDt;
    private Date createDt;
    private Date updateDt;

}
