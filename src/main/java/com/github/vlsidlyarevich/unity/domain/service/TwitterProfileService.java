package com.github.vlsidlyarevich.unity.domain.service;

import com.github.vlsidlyarevich.unity.domain.model.TwitterProfile;

import java.util.List;

public interface TwitterProfileService {

    TwitterProfile findByUrl(String url);

    List<TwitterProfile> findAll();
}
