package com.github.vlsidlyarevich.unity.domain.model;

import com.github.vlsidlyarevich.unity.web.dto.user.UserSocialRequest;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@Builder
@EqualsAndHashCode(callSuper = true)
@Document(collection = "users_social")
public class UserSocial extends DbModel implements Serializable {

    private static final long serialVersionUID = 3134293756973082637L;

    @Id
    private String id;
    private String userId;
    private String firstName;
    private String lastName;
    private String linkedIn;
    private String twitter;
    private String facebook;
    private String email;
    private String skype;
    private String additional;
    private String image;

    public static UserSocial fromDTO(final UserSocialRequest dto) {
        return UserSocial.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .skype(dto.getSkype())
                .additional(dto.getAdditional())
                .image(dto.getImage())
                .build();
    }
}
