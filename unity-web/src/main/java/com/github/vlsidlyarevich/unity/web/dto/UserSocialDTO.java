package com.github.vlsidlyarevich.unity.web.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@EqualsAndHashCode
@NoArgsConstructor
public final class UserSocialDTO implements Serializable {
    private static final long serialVersionUID = -8632737767330638824L;

    private String firstName;
    private String lastName;
    private String email;
    private String skype;
    private String additional;
    private String image;

    public UserSocialDTO(final String firstName, final String lastName,
                         final String email, final String skype,
                         final String additional, final String image) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.skype = skype;
        this.additional = additional;
        this.image = image;
    }
}
