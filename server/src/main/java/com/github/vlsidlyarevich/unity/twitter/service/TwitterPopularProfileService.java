package com.github.vlsidlyarevich.unity.twitter.service;

import com.github.vlsidlyarevich.unity.twitter.model.TwitterPopularProfile;

import java.io.IOException;
import java.util.List;

public interface TwitterPopularProfileService {

    List<TwitterPopularProfile> getPopularProfileList() throws IOException;
}
