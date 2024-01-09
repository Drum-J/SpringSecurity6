package com.springsecurity3.login;

import com.springsecurity3.login.dto.RegisterDTO;
import com.springsecurity3.model.Customer;
import com.springsecurity3.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LoginService {

    private final CustomerRepository customerRepository;

    @Transactional
    public int join(RegisterDTO dto) {
        Customer customer = customerRepository.save(dto.convertEntity());
        return customer.getId();
    }
}
