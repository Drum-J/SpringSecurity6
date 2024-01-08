package com.springsecurity3.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 거래 내역과 잔고 관련 컨트롤러
 */
@RestController
public class BalanceController {

    @GetMapping("/myBalance")
    public String getBalanceDetails() {
        return "Here are the balance details from the DB";
    }
}
