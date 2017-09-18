package com.github.vlsidlyarevich.unity.web.dto.user;

import com.github.vlsidlyarevich.unity.web.pattern.ValidationPatterns;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public final class UserRequest implements Serializable {
    private static final long serialVersionUID = -7579103181077856718L;

    @NotEmpty(message = "Username should not be empty")
    private String username;

    @NotEmpty(message = "User password should not be empty")
    @Pattern(regexp = ValidationPatterns.USER_PASSWORD_PATTERN,
            message = "User password should be at least 4 symbols, 1 upper, "
                    + "1 lower case letter and 1 special")
    private String password;
}
