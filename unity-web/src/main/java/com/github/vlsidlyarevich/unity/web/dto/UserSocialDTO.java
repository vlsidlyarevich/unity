package com.github.vlsidlyarevich.unity.web.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserSocialDTO implements Serializable {
    private static final long serialVersionUID = -8632737767330638824L;

    private String firstName;
    private String lastName;
    private String email;
    private String skype;
    private String additional;
}
