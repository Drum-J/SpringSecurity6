package com.springsecurity3.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class ProjectSecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((requests) -> requests
                .requestMatchers("/myAccount","/myBalance","/myLoans","/myCards").authenticated()
                .requestMatchers("/contact","/notices").permitAll())
                .formLogin(withDefaults())
                .httpBasic(withDefaults());
        return http.build();
    }

    @Bean
    public InMemoryUserDetailsManager inMemory() {

        /*Approach 1 where we use withDefaultPasswordEncoder() method
        while creating the user details*/

        /*
        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("1234")
                .authorities("admin")
                .build();

        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("1234")
                .authorities("read")
                .build();

        return new InMemoryUserDetailsManager(admin, user);
        */

        /*Approach 2 where we use NoOpPasswordEncoder Bean
		while creating the user details*/
        UserDetails admin = User.withUsername("admin")
                .password("1234")
                .authorities("admin")
                .build();

        UserDetails user = User.withUsername("user")
                .password("1234")
                .authorities("read")
                .build();

        return new InMemoryUserDetailsManager(admin, user);
    }

    /**
     * NoOpPasswordEncoder is not recommended for production usage.
     * Use only for non-prod.
     *
     * @return PasswordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

}
