package com.github.vlsidlyarevich.unity.web.controller;

import com.github.vlsidlyarevich.unity.Application;
import com.github.vlsidlyarevich.unity.db.repository.UserRepository;
import com.github.vlsidlyarevich.unity.db.service.UserService;
import com.github.vlsidlyarevich.unity.web.dto.UserDTO;
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
import static org.hamcrest.core.IsNull.notNullValue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class, PersistanceExceptionHandler.class,
        SecurityExceptionHandler.class})
@WebAppConfiguration
public class UserControllerIT extends AbstractControllerIT {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenService tokenService;

    @Before
    public void setUp() {
        prepareTestContextWithAdmin(context);

        userRepository.deleteAll();
        userService.create(user);

        this.token = tokenService.getToken(user.getUsername(), user.getPassword());
    }

    @Test
    public void getAllUsers_Success() throws Exception {
        mvc.perform(MockMvcRequestBuilders.request(HttpMethod.GET, "/api/v1/user")
                .accept(contentType)
                .contentType(contentType)
                .header(SecurityConstants.AUTH_HEADER_NAME, token))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", notNullValue(String.class)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].username", is(user.getUsername())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].password", is(user.getPassword())));
    }

    @Test
    public void getUserById_Success_IfUserPresent() throws Exception {
        mvc.perform(MockMvcRequestBuilders.request(HttpMethod.GET, "/api/v1/user/" + user.getId())
                .accept(contentType)
                .contentType(contentType)
                .header(SecurityConstants.AUTH_HEADER_NAME, token))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", is(user.getId())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.username", is(user.getUsername())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.password", is(user.getPassword())));
    }

    @Test
    public void getUserById_NotFound_IfUserNotPresent() throws Exception {
        mvc.perform(MockMvcRequestBuilders.request(HttpMethod.GET, "/api/v1/user/id0")
                .accept(contentType)
                .contentType(contentType)
                .header(SecurityConstants.AUTH_HEADER_NAME, token))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void addUser_Сreated_IfValidDTO() throws Exception {
        UserDTO dto = new UserDTO("user", "passS$123");

        mvc.perform(MockMvcRequestBuilders.request(HttpMethod.POST, "/api/v1/user")
                .accept(contentType)
                .content(objectMapper.writeValueAsString(dto))
                .contentType(contentType)
                .header(SecurityConstants.AUTH_HEADER_NAME, token))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void addUser_BadRequest_IfEmptyUsername() throws Exception {
        UserDTO dto = new UserDTO("", "passS$123");

        mvc.perform(MockMvcRequestBuilders.request(HttpMethod.POST, "/api/v1/user")
                .accept(contentType)
                .content(objectMapper.writeValueAsString(dto))
                .contentType(contentType)
                .header(SecurityConstants.AUTH_HEADER_NAME, token))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void addUser_BadRequest_IfEmptyPassword() throws Exception {
        UserDTO dto = new UserDTO("user", "");

        mvc.perform(MockMvcRequestBuilders.request(HttpMethod.POST, "/api/v1/user")
                .accept(contentType)
                .content(objectMapper.writeValueAsString(dto))
                .contentType(contentType)
                .header(SecurityConstants.AUTH_HEADER_NAME, token))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void addUser_BadRequest_IfWeakPassword() throws Exception {
        UserDTO dto = new UserDTO("user", "pass");

        mvc.perform(MockMvcRequestBuilders.request(HttpMethod.POST, "/api/v1/user")
                .accept(contentType)
                .content(objectMapper.writeValueAsString(dto))
                .contentType(contentType)
                .header(SecurityConstants.AUTH_HEADER_NAME, token))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void addUser_BadRequest_IfUsernameExists() throws Exception {
        UserDTO dto = new UserDTO(user.getUsername(), "passS$123");

        mvc.perform(MockMvcRequestBuilders.request(HttpMethod.POST, "/api/v1/user")
                .accept(contentType)
                .content(objectMapper.writeValueAsString(dto))
                .contentType(contentType)
                .header(SecurityConstants.AUTH_HEADER_NAME, token))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void updateUserById_Success_IfValidDTO() throws Exception {
        UserDTO dto = new UserDTO("user", "passS$123");

        mvc.perform(MockMvcRequestBuilders.request(HttpMethod.PUT, "/api/v1/user/" + user.getId())
                .accept(contentType)
                .content(objectMapper.writeValueAsString(dto))
                .contentType(contentType)
                .header(SecurityConstants.AUTH_HEADER_NAME, token))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void updateUserById_BadRequest_IfEmptyUsername() throws Exception {
        UserDTO dto = new UserDTO("", "passS$123");

        mvc.perform(MockMvcRequestBuilders.request(HttpMethod.PUT, "/api/v1/user/" + user.getId())
                .accept(contentType)
                .content(objectMapper.writeValueAsString(dto))
                .contentType(contentType)
                .header(SecurityConstants.AUTH_HEADER_NAME, token))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void updateUserById_BadRequest_IfEmptyPassword() throws Exception {
        UserDTO dto = new UserDTO("", "");

        mvc.perform(MockMvcRequestBuilders.request(HttpMethod.PUT, "/api/v1/user/" + user.getId())
                .accept(contentType)
                .content(objectMapper.writeValueAsString(dto))
                .contentType(contentType)
                .header(SecurityConstants.AUTH_HEADER_NAME, token))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void updateUserById_BadRequest_IfWeakPassword() throws Exception {
        UserDTO dto = new UserDTO("user", "pass");

        mvc.perform(MockMvcRequestBuilders.request(HttpMethod.PUT, "/api/v1/user/" + user.getId())
                .accept(contentType)
                .content(objectMapper.writeValueAsString(dto))
                .contentType(contentType)
                .header(SecurityConstants.AUTH_HEADER_NAME, token))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void deleteUserById_Success() throws Exception {
        mvc.perform(MockMvcRequestBuilders.request(HttpMethod.DELETE, "/api/v1/user/" + user.getId())
                .accept(contentType)
                .contentType(contentType)
                .header(SecurityConstants.AUTH_HEADER_NAME, token))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
