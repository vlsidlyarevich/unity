package com.github.vlsidlyarevich.unity.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.vlsidlyarevich.unity.Application;
import com.github.vlsidlyarevich.unity.db.domain.User;
import com.github.vlsidlyarevich.unity.db.service.UserService;
import com.github.vlsidlyarevich.unity.web.dto.LoginDTO;
import com.github.vlsidlyarevich.unity.web.exception.handler.SecurityExceptionHandler;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.Charset;

import static com.github.vlsidlyarevich.unity.TestUtils.createUser;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class, SecurityExceptionHandler.class})
@WebAppConfiguration
public class AuthenticationControllerIT {

    private final MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private UserService userService;

    private User user;

    private MockMvc mvc;

    private ObjectMapper objectMapper;

    @Before
    public void setUp() {
        user = createUser();
        userService.create(user);
        objectMapper = new ObjectMapper();
        this.mvc = webAppContextSetup(context).build();
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

