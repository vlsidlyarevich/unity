package com.github.vlsidlyarevich.unity.web.dto;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Data
public class UserDTO implements Serializable {
    private static final long serialVersionUID = -7579103181077856718L;

    @NotEmpty(message = "Username should not be empty")
    private String username;

    @NotEmpty(message = "User password should not be empty")
    @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{5,}$",
            message = "User password should be at least 4 symbols, 1 upper, 1 lower case letter and 1 special")
    private String password;
}
