package com.github.vlsidlyarevich.unity.web.controller;

import com.github.vlsidlyarevich.unity.Application;
import com.github.vlsidlyarevich.unity.db.service.UserService;
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
import static org.hamcrest.core.IsNull.notNullValue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class, SecurityExceptionHandler.class})
@WebAppConfiguration
public class CurrentUserControllerIT extends AbstractControllerIT {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    @Before
    public void setUp() {
        init(context);
        userService.create(user);
        this.token = tokenService.getToken(user.getUsername(), user.getPassword());
    }

    @Test
    public void getCurrentUser_Forbidden_IfNotAuthenticated() throws Exception {
        mvc.perform(MockMvcRequestBuilders.request(HttpMethod.GET, "/api/v1/user/me")
                .accept(contentType)
                .contentType(contentType))
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    @Test
    public void getCurrentUser_Success_IfAuthenticated() throws Exception {
        mvc.perform(MockMvcRequestBuilders.request(HttpMethod.GET, "/api/v1/user/me")
                .accept(contentType)
                .contentType(contentType)
                .header(SecurityConstants.AUTH_HEADER_NAME, token))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", notNullValue(String.class)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.username", is(user.getUsername())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.password", is(user.getPassword())));
    }
}