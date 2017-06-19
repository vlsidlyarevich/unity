package com.github.vlsidlyarevich.unity.db.domain;

import com.github.vlsidlyarevich.unity.web.dto.UserSocialDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = true)
@Document(collection = "usersSocial")
public class UserSocial extends DbModel implements Serializable {

    private static final long serialVersionUID = 3134293756973082637L;

    @Id
    private String id;
    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    private String skype;
    private String additional;
    private String image;

    public UserSocial(final String firstName,
                      final String lastName, final String email,
                      final String skype, final String additional, final String image) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.skype = skype;
        this.additional = additional;
        this.image = image;
    }

    public static UserSocial fromDTO(final UserSocialDTO dto) {
        return new UserSocial(dto.getFirstName(),
                dto.getLastName(), dto.getEmail(), dto.getSkype(),
                dto.getAdditional(), dto.getImage());
    }
}
