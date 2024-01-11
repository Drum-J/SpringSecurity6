package com.springsecurity7.controller;

import com.springsecurity7.model.Accounts;
import com.springsecurity7.repository.AccountsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 계좌 정보 관련 컨트롤러
 */
@RestController
@RequiredArgsConstructor
public class AccountController {

    private final AccountsRepository accountsRepository;

    @GetMapping("/myAccount")
    public Accounts getAccountDetails(@RequestParam(name = "id") int id) {
        return accountsRepository.findByCustomerId(id);
    }
}
