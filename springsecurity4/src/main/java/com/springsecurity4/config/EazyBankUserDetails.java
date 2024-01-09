package com.springsecurity4.config;

import com.springsecurity4.model.Customer;
import com.springsecurity4.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * CustomUserDetails 와 같다.
 * 서비스 애노테이션을 단 이유, 이 클래스 내부에 비즈니스 로직을 썼으므로 Service Layer로 기능하기 때문
 */
@Service
@RequiredArgsConstructor
public class EazyBankUserDetails implements UserDetailsService {

    private final CustomerRepository customerRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<Customer> findCustomer = customerRepository.findByEmail(username);

        if (findCustomer.isEmpty()) {
            throw new UsernameNotFoundException("해당 유저를 찾을 수 없습니다. Email : " + username);
        } else {
            String email = findCustomer.get(0).getEmail();
            String password = findCustomer.get(0).getPassword();
            String role = findCustomer.get(0).getRole();

            return User.builder()
                    .username(email)
                    .password(password)
                    .authorities(role)
                    .build();
        }
    }
}
