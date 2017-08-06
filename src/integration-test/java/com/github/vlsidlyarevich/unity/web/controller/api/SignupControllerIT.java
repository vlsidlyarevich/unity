package com.github.vlsidlyarevich.unity.web.controller.api;

import com.github.vlsidlyarevich.unity.Application;
import com.github.vlsidlyarevich.unity.db.repository.UserRepository;
import com.github.vlsidlyarevich.unity.web.dto.LoginDTO;
import com.github.vlsidlyarevich.unity.web.dto.UserDTO;
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
        UserDTO dto = new UserDTO("", "password");

        mvc.perform(MockMvcRequestBuilders.request(HttpMethod.POST, "/api/v1/signup")
                .accept(contentType)
                .content(objectMapper.writeValueAsString(dto))
                .contentType(contentType))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void signup_BadRequest_IfEmptyPassword() throws Exception {
        UserDTO dto = new UserDTO("username", "");

        mvc.perform(MockMvcRequestBuilders.request(HttpMethod.POST, "/api/v1/signup")
                .accept(contentType)
                .content(objectMapper.writeValueAsString(dto))
                .contentType(contentType))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void signup_BadRequest_IfWeakPassword() throws Exception {
        UserDTO dto = new UserDTO("username", "password");

        mvc.perform(MockMvcRequestBuilders.request(HttpMethod.POST, "/api/v1/signup")
                .accept(contentType)
                .content(objectMapper.writeValueAsString(dto))
                .contentType(contentType))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void authenticate_Success_IfCorrectUsernameAndPassword() throws Exception {
        LoginDTO dto = new LoginDTO("username", "pasS$123");

        mvc.perform(MockMvcRequestBuilders.request(HttpMethod.POST, "/api/v1/signup")
                .accept(contentType)
                .content(objectMapper.writeValueAsString(dto))
                .contentType(contentType))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
