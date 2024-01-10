package com.springsecurity6.login;

import com.springsecurity6.login.dto.RegisterDTO;
import com.springsecurity6.model.Customer;
import com.springsecurity6.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LoginService {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder encoder;

    @Transactional
    public int join(RegisterDTO dto) {
        Customer customer = dto.convertEntity();
        customer.setHashingPassword(encoder.encode(dto.getPassword()));

        customerRepository.save(customer);

        return customer.getId();
    }

    public Customer getUser(String name) {
        List<Customer> customers = customerRepository.findByEmail(name);

        if (customers.isEmpty()) {
            return null;
        } else {
            return customers.get(0);
        }
    }
}
