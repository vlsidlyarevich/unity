package com.github.vlsidlyarevich.unity.web.dto;

import com.github.vlsidlyarevich.unity.db.domain.UserSocial;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public final class UserSocialDTO implements Serializable {
    private static final long serialVersionUID = -8632737767330638824L;

    private String firstName;
    private String lastName;
    private String email;
    private String skype;
    private String additional;
    private String image;

    public static UserSocialDTO fromDomain(final UserSocial model) {
        UserSocialDTO dto = new UserSocialDTO();
        dto.setFirstName(model.getFirstName() != null ? model.getFirstName() : "");
        dto.setLastName(model.getLastName() != null ? model.getLastName() : "");
        dto.setEmail(model.getEmail() != null ? model.getEmail() : "");
        dto.setSkype(model.getSkype() != null ? model.getSkype() : "");
        dto.setAdditional(model.getAdditional() != null ? model.getAdditional() : "");
        dto.setImage(model.getImage() != null ? model.getImage() : "");

        return dto;
    }
}
