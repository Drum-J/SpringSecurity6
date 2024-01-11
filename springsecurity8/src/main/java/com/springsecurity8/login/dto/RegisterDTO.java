package com.springsecurity8.login.dto;

import com.springsecurity8.model.Customer;
import lombok.Data;

@Data
public class RegisterDTO {
    private String email;
    private String password;
    private String role;
    private String mobileNumber;
    private String name;

    public Customer convertEntity() {
        return new Customer(this.email, this.password, this.role, this.mobileNumber, this.name);
    }
}
