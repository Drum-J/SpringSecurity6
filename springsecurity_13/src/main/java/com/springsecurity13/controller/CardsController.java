package com.springsecurity13.controller;

import com.springsecurity13.model.Cards;
import com.springsecurity13.model.Customer;
import com.springsecurity13.repository.CardsRepository;
import com.springsecurity13.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 카드 내역 관련 컨트롤러
 */
@RestController
@RequiredArgsConstructor
public class CardsController {

    private final CardsRepository cardsRepository;
    private final CustomerRepository customerRepository;

    @GetMapping("/myCards")
    public List<Cards> getCardsDetails(@RequestParam(name = "email") String email) {
        List<Customer> findCustomers = customerRepository.findByEmail(email);
        if (!findCustomers.isEmpty()) {
            return cardsRepository.findByCustomerId(findCustomers.get(0).getId());
        }

        return null;
    }
}
