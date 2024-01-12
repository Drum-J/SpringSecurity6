package com.springsecurity9.controller;

import com.springsecurity9.model.Cards;
import com.springsecurity9.repository.CardsRepository;
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

    @GetMapping("/myCards")
    public List<Cards> getCardsDetails(@RequestParam(name = "id") int id) {
        return cardsRepository.findByCustomerId(id);
    }
}
