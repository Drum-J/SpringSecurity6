package com.springsecurity10.controller;

import com.springsecurity10.model.Contact;
import com.springsecurity10.repository.ContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 문의하기 컨트롤러
 */
@RestController
@RequiredArgsConstructor
public class ContactController {

    private final ContactRepository contactRepository;

    @PostMapping("/contact")
    @PreFilter(value = "filterObject.contactName != 'Test'") // Contact.contactName != Test 인 것만 처리하고 싶다.
    public List<Contact> saveContactInquiryDetails(@RequestBody List<Contact> contacts) {
        //@PreFilter 를 사용하기 위해서는 반환 타입이 컬렉션이어야 한다.
        Contact contact = contacts.get(0);
        contact.setContactId(getServiceReqNumber());
        contact.setCreateDt(new Date(System.currentTimeMillis()));
        contact = contactRepository.save(contact);

        List<Contact> responseContact = new ArrayList<>();
        responseContact.add(contact);
        return responseContact;
    }

    public String getServiceReqNumber() {
        Random random = new Random();
        int ranNum = random.nextInt(999999999 - 9999) + 9999;
        return "SR"+ranNum;
    }
}
