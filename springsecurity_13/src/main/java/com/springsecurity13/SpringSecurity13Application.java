package com.springsecurity13;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@SpringBootApplication
@EnableMethodSecurity(securedEnabled = true,jsr250Enabled = true)
public class SpringSecurity13Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurity13Application.class, args);
    }

}
