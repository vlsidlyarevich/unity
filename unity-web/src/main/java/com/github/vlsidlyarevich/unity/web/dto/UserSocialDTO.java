package com.github.vlsidlyarevich.unity.web.dto;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

@Data
public class UserSocialDTO implements Serializable {
    private static final long serialVersionUID = -8632737767330638824L;

    @NotEmpty(message = "User id should not be empty")
    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    private String skype;
    private String additional;
}
