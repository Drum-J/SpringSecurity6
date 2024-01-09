package com.springsecurity3.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class ProjectSecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests((requests) -> requests
            .requestMatchers("/myAccount","/myBalance","/myLoans","/myCards").authenticated()
            .requestMatchers("/contact","/notices","/register").permitAll())
            .formLogin(withDefaults())
            .httpBasic(withDefaults());
        return http.build();
    }

    /* CustomUserDetailsService 구현으로 주석, CustomUserDetailsService 파일을 여기에 작성하지 않아도 작동하는게 신기했다.
    @Bean
    public UserDetailsService userDetailsService(DataSource dataSource) {
        return new JdbcUserDetailsManager(dataSource);
    }
    */

    /* JDBC 사용을 위해 주석
    @Bean
    public InMemoryUserDetailsManager inMemory() {

        *//*Approach 1 where we use withDefaultPasswordEncoder() method
        while creating the user details*//*

        *//*
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
        *//*

        *//*Approach 2 where we use NoOpPasswordEncoder Bean
		while creating the user details*//*
        UserDetails admin = User.withUsername("admin")
                .password("1234")
                .authorities("admin")
                .build();

        UserDetails user = User.withUsername("user")
                .password("1234")
                .authorities("read")
                .build();

        return new InMemoryUserDetailsManager(admin, user);
    }*/

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


    //H2 DB 가 Security를 통과하지 않도록
    @Bean
    @ConditionalOnProperty(name = "spring.h2.console.enabled",havingValue = "true")
    public WebSecurityCustomizer configureH2ConsoleEnable() {
        return web -> web.ignoring()
                .requestMatchers(PathRequest.toH2Console());
    }
}
