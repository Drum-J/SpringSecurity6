package com.springsecurity10;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@SpringBootApplication
@EnableMethodSecurity(prePostEnabled = true,securedEnabled = true,jsr250Enabled = true)
//securedEnabled, jsr250Enabled 는 false가 기본값
public class SpringSecurity10Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurity10Application.class, args);
    }

}
