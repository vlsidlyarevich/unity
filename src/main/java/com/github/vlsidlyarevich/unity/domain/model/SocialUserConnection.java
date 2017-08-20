package com.github.vlsidlyarevich.unity.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@Document(collection = "social_users_connections")
public class SocialUserConnection implements Serializable {

    private static final long serialVersionUID = 8265374700528361079L;

    @Id
    private String id;
    private String userId;
    private String providerId;
    private String providerUserId;
    private Long rank;
    private String displayName;
    private String profileURL;
    private String imageURL;
    private String accessToken;
    private String secret;
    private String refreshToken;
    private Long expireTime;
}
