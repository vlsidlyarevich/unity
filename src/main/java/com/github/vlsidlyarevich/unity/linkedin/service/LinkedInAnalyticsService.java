package com.github.vlsidlyarevich.unity.linkedin.service;

import com.github.vlsidlyarevich.unity.domain.model.UserAnalytics;

public interface LinkedInAnalyticsService {

    UserAnalytics getLinkedInProfileAnalytics(String userUrl);
}
