package com.springsecurity6.login;

import com.springsecurity6.login.dto.RegisterDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody RegisterDTO dto) {
        int customerId = loginService.join(dto);

        if (customerId > 0) {
            return ResponseEntity
                    .status(CREATED)
                    .body("회원 가입이 정상적으로 완료 되었습니다.");
        }

        return ResponseEntity.status(INTERNAL_SERVER_ERROR)
                .body("회원 가입에 실패했습니다.");
    }
}
