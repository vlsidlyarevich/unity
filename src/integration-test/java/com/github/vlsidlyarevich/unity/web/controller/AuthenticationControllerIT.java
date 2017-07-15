package com.github.vlsidlyarevich.unity.web.controller;

import com.github.vlsidlyarevich.unity.Application;
import com.github.vlsidlyarevich.unity.db.service.UserService;
import com.github.vlsidlyarevich.unity.web.dto.LoginDTO;
import com.github.vlsidlyarevich.unity.web.exception.handler.SecurityExceptionHandler;
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

import static org.hamcrest.core.IsNull.notNullValue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class, SecurityExceptionHandler.class})
@WebAppConfiguration
public class AuthenticationControllerIT extends AbstractControllerIT {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private UserService userService;

    @Before
    public void setUp() {
        prepareTestContext(context);
        userService.create(user);
    }

    @Test
    public void authenticate_Unauthorized_IfBadCredentials() throws Exception {
        LoginDTO dto = new LoginDTO("bad username", "bad password");

        mvc.perform(MockMvcRequestBuilders.request(HttpMethod.POST, "/api/v1/auth")
                .accept(contentType)
                .content(objectMapper.writeValueAsString(dto))
                .contentType(contentType))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

    @Test
    public void authenticate_Success_IfCorrectCredentials() throws Exception {
        LoginDTO dto = new LoginDTO(user.getUsername(), user.getPassword());

        mvc.perform(MockMvcRequestBuilders.request(HttpMethod.POST, "/api/v1/auth")
                .accept(contentType)
                .content(objectMapper.writeValueAsString(dto))
                .contentType(contentType))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.token", notNullValue(String.class)));
    }
}

