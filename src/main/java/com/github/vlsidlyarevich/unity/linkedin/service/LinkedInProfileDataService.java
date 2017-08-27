package com.github.vlsidlyarevich.unity.linkedin.service;

import com.github.vlsidlyarevich.unity.linkedin.model.LinkedInProfileData;

import java.util.Optional;

public interface LinkedInProfileDataService {

    Optional<LinkedInProfileData> getLinkedInProfileData(String userUrl);
}
