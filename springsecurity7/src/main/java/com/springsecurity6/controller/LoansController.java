package com.springsecurity6.controller;

import com.springsecurity6.model.Loans;
import com.springsecurity6.repository.LoanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 대출 관련 컨트롤러
 */
@RestController
@RequiredArgsConstructor
public class LoansController {

    private final LoanRepository loanRepository;

    @GetMapping("/myLoans")
    public List<Loans> getLoanDetails(@RequestParam(name = "id") int id) {
        return loanRepository.findByCustomerIdOrderByStartDtDesc(id);
    }
}
