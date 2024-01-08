package com.springsecurity3.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 계좌 정보 관련 컨트롤러
 */
@RestController
public class AccountController {

    @GetMapping("/myAccount")
    public String getAccountDetails() {
        return "Here are the account details from the DB";
    }
}
