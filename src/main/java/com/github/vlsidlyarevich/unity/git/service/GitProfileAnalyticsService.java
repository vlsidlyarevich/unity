package com.github.vlsidlyarevich.unity.git.service;

import com.github.vlsidlyarevich.unity.db.domain.UserAnalytics;

public interface GitProfileAnalyticsService {

    UserAnalytics getGitProfileAnalytics(String gitLogin);
}
