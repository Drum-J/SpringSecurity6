package com.springsecurity10.controller;

import com.springsecurity10.model.AccountTransactions;
import com.springsecurity10.repository.AccountTransactionsRepository;
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

    @GetMapping("/myBalance")
    public List<AccountTransactions> getBalanceDetails(@RequestParam(name = "id") int id) {

        return accountTransactionsRepository
                .findByCustomerIdOrderByTransactionDtDesc(id);
    }
}
