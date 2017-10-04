package com.github.vlsidlyarevich.unity.domain.service;

import com.github.vlsidlyarevich.unity.domain.model.TwitterProfile;

import java.util.List;

public interface TwitterProfileService {

    TwitterProfile findByScreenName(String screenName);

    List<TwitterProfile> findAll();
}
