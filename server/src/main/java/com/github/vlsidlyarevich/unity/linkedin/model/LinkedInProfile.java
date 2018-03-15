package com.github.vlsidlyarevich.unity.linkedin.model;

import com.github.vlsidlyarevich.unity.common.model.LinkedInResult;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class LinkedInProfile extends LinkedInResult {

    private static final long serialVersionUID = 809941529441320372L;

    private String id;
    private String firstName;
    private String lastName;
    private String headline;
    private String industry;
    private String emailAddress;
    private LinkedInProfileUrlResource siteStandardProfileRequest;
    private String publicProfileUrl;
    private String profilePictureUrl;
    private String summary;
}
