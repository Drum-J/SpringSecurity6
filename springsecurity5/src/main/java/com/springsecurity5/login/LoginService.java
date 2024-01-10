package com.springsecurity5.login;

import com.springsecurity5.login.dto.RegisterDTO;
import com.springsecurity5.model.Customer;
import com.springsecurity5.repository.CustomerRepository;
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
