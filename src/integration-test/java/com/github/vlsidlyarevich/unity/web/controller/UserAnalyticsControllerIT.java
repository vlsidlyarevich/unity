package com.github.vlsidlyarevich.unity.web.controller;

import com.github.vlsidlyarevich.unity.Application;
import com.github.vlsidlyarevich.unity.db.domain.UserAnalytics;
import com.github.vlsidlyarevich.unity.db.repository.UserRepository;
import com.github.vlsidlyarevich.unity.db.service.UserAnalyticsService;
import com.github.vlsidlyarevich.unity.db.service.UserService;
import com.github.vlsidlyarevich.unity.web.exception.handler.PersistanceExceptionHandler;
import com.github.vlsidlyarevich.unity.web.exception.handler.SecurityExceptionHandler;
import com.github.vlsidlyarevich.unity.web.security.service.TokenService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;

import static com.github.vlsidlyarevich.unity.TestUtils.createUserAnalytics;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class, PersistanceExceptionHandler.class,
        SecurityExceptionHandler.class})
@WebAppConfiguration
public class UserAnalyticsControllerIT extends AbstractControllerIT {

    private UserAnalytics analytics;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserAnalyticsService analyticsService;

    @Before
    public void setUp() {
        prepareTestContextWithAdmin(context);
        analytics = createUserAnalytics();

        userRepository.deleteAll();
        userService.create(user);

        this.token = tokenService.getToken(user.getUsername(), user.getPassword());
    }

    @Test
    public void getAnalyticsByUserId_Success_IfAnalyticsAndUserPresent() {

    }

    public void getAnalyticsByUserId_BadRequest_IfUserNotPresent() {

    }

    public void getAnalyticsReportById_Success_IfAnalyticsAndUserPresent() {

    }

    public void getAnalyticsReportById_NoContent_IfAnalyticsNotPresent() {

    }

    public void getAnalyticsReportById_BadRequest_IfUserNotPresent() {

    }

    public void deleteAnalyticsReportById_Success_IfAnalyticsAndUserPresent() {

    }

    public void deleteAnalyticsReportById_BadRequest_IfUserNotPresent() {

    }

    public void deleteAllAnalyticsReports_Success_IfUserPresent() {

    }

    public void deleteAllAnalyticsReports_BadRequest_IfUserNotPresent() {

    }
}
