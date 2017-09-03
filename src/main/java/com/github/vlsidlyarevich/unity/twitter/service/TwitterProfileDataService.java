package com.github.vlsidlyarevich.unity.twitter.service;

import com.github.vlsidlyarevich.unity.twitter.model.TwitterProfileData;

import java.util.Optional;

public interface TwitterProfileDataService {

    Optional<TwitterProfileData> getTwitterProfileData(String username);
}
