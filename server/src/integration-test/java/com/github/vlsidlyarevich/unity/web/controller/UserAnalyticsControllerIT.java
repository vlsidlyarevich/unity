package com.github.vlsidlyarevich.unity.web.controller;

import com.github.vlsidlyarevich.unity.Application;
import com.github.vlsidlyarevich.unity.domain.model.UserAnalytics;
import com.github.vlsidlyarevich.unity.domain.repository.UserRepository;
import com.github.vlsidlyarevich.unity.domain.service.UserAnalyticsService;
import com.github.vlsidlyarevich.unity.domain.service.UserService;
import com.github.vlsidlyarevich.unity.web.exception.handler.PersistenceExceptionHandler;
import com.github.vlsidlyarevich.unity.web.exception.handler.SecurityExceptionHandler;
import com.github.vlsidlyarevich.unity.web.security.constant.SecurityConstants;
import com.github.vlsidlyarevich.unity.web.security.service.TokenService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.WebApplicationContext;

import static com.github.vlsidlyarevich.unity.TestUtils.createUserAnalytics;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class, PersistenceExceptionHandler.class,
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

        userRepository.deleteAll();
        userService.create(user);

        analytics = createUserAnalytics();
        analytics.setUserId(user.getId());
        analyticsService.add(analytics);

        this.token = tokenService.getToken(user.getUsername(), user.getPassword());
    }

    @Test
    public void getAnalyticsByUserId_Success_IfAnalyticsAndUserPresent() throws Exception {
        mvc.perform(MockMvcRequestBuilders.request(HttpMethod.GET, "/api/v1/user/" + user.getId() + "/analytics")
                .accept(contentType)
                .contentType(contentType)
                .header(SecurityConstants.AUTH_HEADER_NAME, token))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", is(analytics.getId())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.userId", is(analytics.getUserId())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.createdAt", is(analytics.getCreatedAt().getTime())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.updatedAt", is(analytics.getUpdatedAt().getTime())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.reports[0].result", is(analytics.getReports().get(0).getResult())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.reports[0].id", is(analytics.getReports().get(0).getId())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.reports[0].resource", is(analytics.getReports().get(0).getResource().toString())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.reports[0].analyzedAt", is(analytics.getReports().get(0).getAnalyzedAt().getTime())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.reports[0].analysisTime", is(analytics.getReports().get(0).getAnalysisTime().intValue())));
    }

    @Test
    public void getAnalyticsByUserId_NotFound_IfUserNotPresent() throws Exception {
        mvc.perform(MockMvcRequestBuilders.request(HttpMethod.GET, "/api/v1/user/id0/analytics")
                .accept(contentType)
                .contentType(contentType)
                .header(SecurityConstants.AUTH_HEADER_NAME, token))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void getAnalyticsReportById_Success_IfAnalyticsAndUserPresent() throws Exception {
        mvc.perform(MockMvcRequestBuilders.request(HttpMethod.GET, "/api/v1/user/" + user.getId()
                + "/analytics/" + analytics.getReports().get(0).getId())
                .accept(contentType)
                .contentType(contentType)
                .header(SecurityConstants.AUTH_HEADER_NAME, token))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", is(analytics.getReports().get(0).getId())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.analysisTime", is(analytics.getReports().get(0).getAnalysisTime().intValue())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.analyzedAt", is(analytics.getReports().get(0).getAnalyzedAt().getTime())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.resource", is(analytics.getReports().get(0).getResource().toString())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.result", is(analytics.getReports().get(0).getResult())));
    }

    @Test
    public void getAnalyticsReportById_NoContent_IfAnalyticsNotPresent() throws Exception {
        mvc.perform(MockMvcRequestBuilders.request(HttpMethod.GET, "/api/v1/user/" + user.getId() + "/analytics/id0")
                .accept(contentType)
                .contentType(contentType)
                .header(SecurityConstants.AUTH_HEADER_NAME, token))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void getAnalyticsReportById_NotFound_IfUserNotPresent() throws Exception {
        mvc.perform(MockMvcRequestBuilders.request(HttpMethod.GET, "/api/v1/user/id0/analytics/id0")
                .accept(contentType)
                .contentType(contentType)
                .header(SecurityConstants.AUTH_HEADER_NAME, token))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void deleteAnalyticsReportById_Success_IfAnalyticsAndUserPresent() throws Exception {
        mvc.perform(MockMvcRequestBuilders.request(HttpMethod.DELETE, "/api/v1/user/" + user.getId() + "/analytics/" + analytics.getId())
                .accept(contentType)
                .contentType(contentType)
                .header(SecurityConstants.AUTH_HEADER_NAME, token))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void deleteAnalyticsReportById_NotFound_IfUserNotPresent() throws Exception {
        mvc.perform(MockMvcRequestBuilders.request(HttpMethod.DELETE, "/api/v1/user/id0/analytics/id0")
                .accept(contentType)
                .contentType(contentType)
                .header(SecurityConstants.AUTH_HEADER_NAME, token))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void deleteAllAnalyticsReports_Success_IfUserPresent() throws Exception {
        mvc.perform(MockMvcRequestBuilders.request(HttpMethod.DELETE, "/api/v1/user/" + user.getId() + "/analytics/all")
                .accept(contentType)
                .contentType(contentType)
                .header(SecurityConstants.AUTH_HEADER_NAME, token))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void deleteAllAnalyticsReports_NotFound_IfUserNotPresent() throws Exception {
        mvc.perform(MockMvcRequestBuilders.request(HttpMethod.DELETE, "/api/v1/user/id0/analytics/all")
                .accept(contentType)
                .contentType(contentType)
                .header(SecurityConstants.AUTH_HEADER_NAME, token))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}
