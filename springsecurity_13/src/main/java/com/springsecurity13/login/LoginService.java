package com.springsecurity13.login;

import com.springsecurity13.model.Customer;
import com.springsecurity13.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LoginService {

    private final CustomerRepository customerRepository;

    public Customer getUser(String name) {
        List<Customer> customers = customerRepository.findByEmail(name);

        if (customers.isEmpty()) {
            return null;
        } else {
            return customers.get(0);
        }
    }
}
