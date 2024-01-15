package com.springsecurity13.login;

import com.springsecurity13.login.dto.RegisterDTO;
import com.springsecurity13.model.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @GetMapping("/user")
    public Customer getUserDetailsAfterLogin(Authentication authentication) {
        return loginService.getUser(authentication.getName());
    }
}
