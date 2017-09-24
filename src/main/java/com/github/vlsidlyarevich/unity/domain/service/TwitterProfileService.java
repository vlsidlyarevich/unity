package com.github.vlsidlyarevich.unity.domain.service;

import com.github.vlsidlyarevich.unity.domain.model.TwitterProfile;

import java.util.List;

public interface TwitterProfileService {

    List<TwitterProfile> findAll();
}
