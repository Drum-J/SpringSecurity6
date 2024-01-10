package com.springsecurity4.login;

import com.springsecurity4.login.dto.RegisterDTO;
import com.springsecurity4.model.Customer;
import com.springsecurity4.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
