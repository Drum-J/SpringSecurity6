package com.springsecurity13.controller;

import com.springsecurity13.model.AccountTransactions;
import com.springsecurity13.model.Customer;
import com.springsecurity13.repository.AccountTransactionsRepository;
import com.springsecurity13.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 거래 내역과 잔고 관련 컨트롤러
 */
@RestController
@RequiredArgsConstructor
public class BalanceController {

    private final AccountTransactionsRepository accountTransactionsRepository;
    private final CustomerRepository customerRepository;

    @GetMapping("/myBalance")
    public List<AccountTransactions> getBalanceDetails(@RequestParam(name = "email") String email) {

        List<Customer> findCustomers = customerRepository.findByEmail(email);
        if (!findCustomers.isEmpty()) {
            return accountTransactionsRepository
                    .findByCustomerIdOrderByTransactionDtDesc(findCustomers.get(0).getId());
        }

        return null;
    }
}
