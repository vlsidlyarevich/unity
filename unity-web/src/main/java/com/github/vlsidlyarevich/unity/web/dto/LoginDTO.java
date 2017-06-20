package com.github.vlsidlyarevich.unity.web.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

@Data
@NoArgsConstructor
public final class LoginDTO implements Serializable {
    private static final long serialVersionUID = -3269151399045119448L;

    @NotEmpty(message = "Username should not be empty")
    private String username;
    @NotEmpty(message = "User password should not be empty")
    private String password;

    public LoginDTO(final String username, final String password) {
        this.username = username;
        this.password = password;
    }
}
