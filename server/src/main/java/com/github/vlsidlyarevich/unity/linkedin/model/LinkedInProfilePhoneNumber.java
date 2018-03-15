package com.github.vlsidlyarevich.unity.linkedin.model;

import com.github.vlsidlyarevich.unity.common.model.LinkedInResult;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class LinkedInProfilePhoneNumber extends LinkedInResult {

    private static final long serialVersionUID = 654975300541916115L;

    private String phoneType;
    private String phoneNumber;
}
