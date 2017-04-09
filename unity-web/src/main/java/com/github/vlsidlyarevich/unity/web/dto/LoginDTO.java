package com.github.vlsidlyarevich.unity.web.dto;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

@Data
public class LoginDTO implements Serializable {

    @NotEmpty(message = "Username should not be empty")
    private String username;
    @NotEmpty(message = "User password should not be empty")
    private String password;

    public LoginDTO() {
    }
}
