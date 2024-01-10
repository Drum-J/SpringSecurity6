package com.springsecurity5.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 문의하기 컨트롤러
 */
@RestController
public class ContactController {

    @GetMapping("/contact")
    public String saveContactInquiryDetails() {
        return "Inquiry details are saved to the DB";
    }
}
