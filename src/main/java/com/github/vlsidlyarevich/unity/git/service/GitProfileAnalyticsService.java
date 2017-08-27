package com.github.vlsidlyarevich.unity.git.service;

import com.github.vlsidlyarevich.unity.domain.model.UserAnalytics;

public interface GitProfileAnalyticsService {

    UserAnalytics getGitProfileAnalytics(String username);
}
