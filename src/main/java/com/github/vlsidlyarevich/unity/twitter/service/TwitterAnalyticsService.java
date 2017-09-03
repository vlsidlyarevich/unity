package com.github.vlsidlyarevich.unity.twitter.service;

import com.github.vlsidlyarevich.unity.domain.model.UserAnalytics;

public interface TwitterAnalyticsService {

    UserAnalytics getTwitterProfileAnalytics(String username);
}
