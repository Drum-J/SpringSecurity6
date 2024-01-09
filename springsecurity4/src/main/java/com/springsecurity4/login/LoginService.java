package com.springsecurity4.login;

import com.springsecurity4.login.dto.RegisterDTO;
import com.springsecurity4.model.Customer;
import com.springsecurity4.repository.CustomerRepository;
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
