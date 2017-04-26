package com.github.vlsidlyarevich.unity.db.service;

import com.github.vlsidlyarevich.unity.db.model.UserAnalytics;

import java.util.List;

public interface UserAnalyticsService {

    UserAnalytics add(UserAnalytics object);

    UserAnalytics find(String id);

    UserAnalytics findByUserId(String userId);

    List<UserAnalytics> findAll();

    String delete(String id);
}
