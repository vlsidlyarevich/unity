package com.github.vlsidlyarevich.unity.domain.service;

import com.github.vlsidlyarevich.unity.common.model.AnalysisReport;
import com.github.vlsidlyarevich.unity.domain.model.UserAnalytics;

import java.util.List;

public interface UserAnalyticsService {

    UserAnalytics add(UserAnalytics object);

    UserAnalytics find(String id);

    UserAnalytics findByUserId(String userId);

    AnalysisReport findReportById(String userId, String reportId);

    List<UserAnalytics> findAll();

    String delete(String id);

    String deleteReport(String userId, String reportId);

    List<String> deleteAll();

    List<String> deleteAllReports(String userId);
}
