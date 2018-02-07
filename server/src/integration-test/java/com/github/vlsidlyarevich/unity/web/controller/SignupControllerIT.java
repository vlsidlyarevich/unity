package com.github.vlsidlyarevich.unity.web.controller;

import com.github.vlsidlyarevich.unity.Application;
import com.github.vlsidlyarevich.unity.domain.repository.UserRepository;
import com.github.vlsidlyarevich.unity.web.dto.jwt.JwtAuthenticationRequest;
import com.github.vlsidlyarevich.unity.web.dto.user.UserRequest;
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

import java.util.ArrayList;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class, SecurityExceptionHandler.class})
@WebAppConfiguration
public class SignupControllerIT extends AbstractControllerIT {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private UserRepository userRepository;

    @Before
    public void setUp() {
        prepareTestContextWithUser(context);
        userRepository.deleteAll();
    }

    @Test
    public void signup_BadRequest_IfEmptyUsername() throws Exception {
        UserRequest dto = new UserRequest("", "password", new ArrayList<>(), false, false, false);

        mvc.perform(MockMvcRequestBuilders.request(HttpMethod.POST, "/api/v1/signup")
                .accept(contentType)
                .content(objectMapper.writeValueAsString(dto))
                .contentType(contentType))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void signup_BadRequest_IfEmptyPassword() throws Exception {
        UserRequest dto = new UserRequest("username", "", new ArrayList<>(), false, false, false);

        mvc.perform(MockMvcRequestBuilders.request(HttpMethod.POST, "/api/v1/signup")
                .accept(contentType)
                .content(objectMapper.writeValueAsString(dto))
                .contentType(contentType))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void signup_BadRequest_IfWeakPassword() throws Exception {
        UserRequest dto = new UserRequest("username", "password", new ArrayList<>(), false, false, false);

        mvc.perform(MockMvcRequestBuilders.request(HttpMethod.POST, "/api/v1/signup")
                .accept(contentType)
                .content(objectMapper.writeValueAsString(dto))
                .contentType(contentType))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void authenticate_Success_IfCorrectUsernameAndPassword() throws Exception {
        JwtAuthenticationRequest request = new JwtAuthenticationRequest("username", "pasS$123");

        mvc.perform(MockMvcRequestBuilders.request(HttpMethod.POST, "/api/v1/signup")
                .accept(contentType)
                .content(objectMapper.writeValueAsString(request))
                .contentType(contentType))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
