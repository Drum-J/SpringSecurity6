package com.springsecurity13.controller;

import com.springsecurity13.model.Loans;
import com.springsecurity13.repository.LoanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PostAuthorize;
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
    @PostAuthorize("hasRole('USER')")
    public List<Loans> getLoanDetails(@RequestParam(name = "id") int id) {
        return loanRepository.findByCustomerIdOrderByStartDtDesc(id);
    }
}
