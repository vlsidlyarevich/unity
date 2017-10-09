package com.github.vlsidlyarevich.unity.web.dto.jwt;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public final class JwtAuthenticationRequest implements Serializable {
    private static final long serialVersionUID = -3269151399045119448L;

    @NotEmpty(message = "Username should not be empty")
    private String username;
    @NotEmpty(message = "User password should not be empty")
    private String password;
}
