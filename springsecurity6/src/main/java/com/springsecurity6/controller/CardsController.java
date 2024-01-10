package com.springsecurity6.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 카드 내역 관련 컨트롤러
 */
@RestController
public class CardsController {

    @GetMapping("/myCards")
    public String getCardsDetails() {
        return "Here are the cards details from the DB";
    }
}
