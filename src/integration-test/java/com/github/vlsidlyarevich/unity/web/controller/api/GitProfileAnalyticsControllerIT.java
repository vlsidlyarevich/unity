package com.github.vlsidlyarevich.unity.web.controller.api;

import com.github.vlsidlyarevich.unity.Application;
import com.github.vlsidlyarevich.unity.common.model.AnalyzedResource;
import com.github.vlsidlyarevich.unity.domain.service.UserService;
import com.github.vlsidlyarevich.unity.web.exception.handler.PersistanceExceptionHandler;
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

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class, SecurityExceptionHandler.class,
        PersistanceExceptionHandler.class})
@WebAppConfiguration
public class GitProfileAnalyticsControllerIT extends AbstractControllerIT {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    @Before
    public void setUp() {
        prepareTestContextWithAdmin(context);

        userService.create(user);

        this.token = tokenService.getToken(user.getUsername(), user.getPassword());
    }

    @Test
    public void getGitData_Success_IfLoginPresent() throws Exception {
        mvc.perform(MockMvcRequestBuilders.request(HttpMethod.GET, "/api/v1/git/profile/vlsidlyarevich")
                .accept(contentType)
                .contentType(contentType)
                .header(SecurityConstants.AUTH_HEADER_NAME, token))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", notNullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.userId", is(user.getId())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.createdAt", notNullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.updatedAt", notNullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.reports[0].result", notNullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.reports[0].id", notNullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.reports[0].resource", is(AnalyzedResource.GITHUB.toString())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.reports[0].analyzedAt", notNullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.reports[0].analysisTime", notNullValue()));
    }

    @Test
    public void getGitData_NotFound_IfLoginNotPresent() throws Exception {
        mvc.perform(MockMvcRequestBuilders.request(HttpMethod.GET, "/api/v1/git/profile/nosuchloginexists")
                .accept(contentType)
                .contentType(contentType)
                .header(SecurityConstants.AUTH_HEADER_NAME, token))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}
