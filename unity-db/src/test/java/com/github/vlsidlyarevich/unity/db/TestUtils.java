package com.github.vlsidlyarevich.unity.db;

import com.github.vlsidlyarevich.unity.common.model.AnalysisReport;
import com.github.vlsidlyarevich.unity.db.domain.Authority;
import com.github.vlsidlyarevich.unity.db.domain.User;
import com.github.vlsidlyarevich.unity.db.domain.UserAnalytics;
import com.github.vlsidlyarevich.unity.db.domain.UserSocial;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public final class TestUtils {

    private TestUtils() {

    }

    public static UserAnalytics createUserAnalytics() {
        List<AnalysisReport> reports = new ArrayList<>();

        return new UserAnalytics(TestRandomUtils.getRandomString(8), reports);
    }

    public static AnalysisReport createAnalysisReport() {
        return new AnalysisReport(null, new Date(), 1L);
    }

    public static User createUser() {
        List<Authority> authorities = new ArrayList<>();
        authorities.add(Authority.ROLE_USER);

        return new User(authorities, TestRandomUtils.getRandomString(8), TestRandomUtils.getRandomString(8),
                false, false, false,
                true);
    }

    public static UserSocial createUserSocial() {
        return new UserSocial(TestRandomUtils.getRandomString(8),
                TestRandomUtils.getRandomString(8),
                TestRandomUtils.getRandomString(8),
                TestRandomUtils.getRandomString(8),
                TestRandomUtils.getRandomString(8),
                TestRandomUtils.getRandomString(8),
                TestRandomUtils.getRandomString(8));
    }
}
