package com.springsecurity5.login.dto;

import com.springsecurity5.model.Customer;
import lombok.Data;

@Data
public class RegisterDTO {
    private String email;
    private String password;
    private String role;

    public Customer convertEntity() {
        return new Customer(this.email, this.password, this.role);
    }
}
