package com.springsecurity13.controller;

import com.springsecurity13.model.Accounts;
import com.springsecurity13.model.Customer;
import com.springsecurity13.repository.AccountsRepository;
import com.springsecurity13.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 계좌 정보 관련 컨트롤러
 */
@RestController
@RequiredArgsConstructor
public class AccountController {

    private final AccountsRepository accountsRepository;
    private final CustomerRepository customerRepository;

    @GetMapping("/myAccount")
    public Accounts getAccountDetails(@RequestParam(name = "email") String email) {
        List<Customer> findCustomers = customerRepository.findByEmail(email);
        if (!findCustomers.isEmpty()) {
            return accountsRepository.findByCustomerId(findCustomers.get(0).getId());
        }

        return null;
    }
}
