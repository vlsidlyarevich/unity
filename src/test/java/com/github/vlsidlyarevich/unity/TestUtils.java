package com.github.vlsidlyarevich.unity;

import com.github.vlsidlyarevich.unity.common.model.AnalysisReport;
import com.github.vlsidlyarevich.unity.web.security.model.Authority;
import com.github.vlsidlyarevich.unity.domain.model.User;
import com.github.vlsidlyarevich.unity.domain.model.UserAnalytics;
import com.github.vlsidlyarevich.unity.domain.model.UserSocial;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public final class TestUtils {

    private TestUtils() {

    }

    public static UserAnalytics createUserAnalytics() {
        final List<AnalysisReport> reports = new ArrayList<>();
        reports.add(createAnalysisReport());

        return new UserAnalytics(TestRandomUtils.getRandomString(8), reports);
    }

    public static AnalysisReport createAnalysisReport() {
        return new AnalysisReport(null, new Date(), 1L);
    }

    public static User createUser() {
        final List<Authority> authorities = new ArrayList<>();
        authorities.add(Authority.ROLE_USER);

        return User.builder()
                .authorities(authorities)
                .username(TestRandomUtils.getRandomString(8))
                .password(TestRandomUtils.getRandomString(8))
                .accountNonExpired(true)
                .accountNonLocked(true)
                .isEnabled(true)
                .credentialsNonExpired(true)
                .build();
    }

    public static User createAdmin() {
        final List<Authority> authorities = new ArrayList<>();
        authorities.add(Authority.ROLE_ADMIN);

        return User.builder()
                .authorities(authorities)
                .username(TestRandomUtils.getRandomString(8))
                .password(TestRandomUtils.getRandomString(8))
                .accountNonExpired(true)
                .accountNonLocked(true)
                .isEnabled(true)
                .credentialsNonExpired(true)
                .build();
    }

    public static UserSocial createUserSocial() {
        return new UserSocial(TestRandomUtils.getRandomString(8),
                TestRandomUtils.getRandomString(8),
                TestRandomUtils.getRandomString(8),
                TestRandomUtils.getRandomString(8),
                TestRandomUtils.getRandomString(8),
                TestRandomUtils.getRandomString(8));
    }
}
