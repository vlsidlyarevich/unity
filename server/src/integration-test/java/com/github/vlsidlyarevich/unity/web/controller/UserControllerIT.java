package com.github.vlsidlyarevich.unity.web.controller;

import com.github.vlsidlyarevich.unity.Application;
import com.github.vlsidlyarevich.unity.web.security.model.Authority;
import com.github.vlsidlyarevich.unity.domain.repository.UserRepository;
import com.github.vlsidlyarevich.unity.domain.service.UserService;
import com.github.vlsidlyarevich.unity.web.dto.user.UserRequest;
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

import java.util.stream.Collectors;

import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class, PersistenceExceptionHandler.class,
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
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", is(user.getId())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].username", is(user.getUsername())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].password", is(user.getPassword())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].createdAt", is(user.getCreatedAt().getTime())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].updatedAt", is(user.getUpdatedAt().getTime())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].authorities", is(user.getAuthorities()
                        .stream()
                        .map(Authority::getAuthority)
                        .collect(Collectors.toList()))));
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
                .andExpect(MockMvcResultMatchers.jsonPath("$.password", is(user.getPassword())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.createdAt", is(user.getCreatedAt().getTime())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.updatedAt", is(user.getUpdatedAt().getTime())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.authorities", is(user.getAuthorities()
                        .stream()
                        .map(Authority::getAuthority)
                        .collect(Collectors.toList()))));
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
        UserRequest dto = new UserRequest("user", "passS$123");

        mvc.perform(MockMvcRequestBuilders.request(HttpMethod.POST, "/api/v1/user")
                .accept(contentType)
                .content(objectMapper.writeValueAsString(dto))
                .contentType(contentType)
                .header(SecurityConstants.AUTH_HEADER_NAME, token))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void addUser_BadRequest_IfEmptyUsername() throws Exception {
        UserRequest dto = new UserRequest("", "passS$123");

        mvc.perform(MockMvcRequestBuilders.request(HttpMethod.POST, "/api/v1/user")
                .accept(contentType)
                .content(objectMapper.writeValueAsString(dto))
                .contentType(contentType)
                .header(SecurityConstants.AUTH_HEADER_NAME, token))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void addUser_BadRequest_IfEmptyPassword() throws Exception {
        UserRequest dto = new UserRequest("user", "");

        mvc.perform(MockMvcRequestBuilders.request(HttpMethod.POST, "/api/v1/user")
                .accept(contentType)
                .content(objectMapper.writeValueAsString(dto))
                .contentType(contentType)
                .header(SecurityConstants.AUTH_HEADER_NAME, token))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void addUser_BadRequest_IfWeakPassword() throws Exception {
        UserRequest dto = new UserRequest("user", "pass");

        mvc.perform(MockMvcRequestBuilders.request(HttpMethod.POST, "/api/v1/user")
                .accept(contentType)
                .content(objectMapper.writeValueAsString(dto))
                .contentType(contentType)
                .header(SecurityConstants.AUTH_HEADER_NAME, token))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void addUser_BadRequest_IfUsernameExists() throws Exception {
        UserRequest dto = new UserRequest(user.getUsername(), "passS$123");

        mvc.perform(MockMvcRequestBuilders.request(HttpMethod.POST, "/api/v1/user")
                .accept(contentType)
                .content(objectMapper.writeValueAsString(dto))
                .contentType(contentType)
                .header(SecurityConstants.AUTH_HEADER_NAME, token))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void updateUserById_Success_IfValidDTO() throws Exception {
        UserRequest dto = new UserRequest("user", "passS$123");

        mvc.perform(MockMvcRequestBuilders.request(HttpMethod.PUT, "/api/v1/user/" + user.getId())
                .accept(contentType)
                .content(objectMapper.writeValueAsString(dto))
                .contentType(contentType)
                .header(SecurityConstants.AUTH_HEADER_NAME, token))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void updateUserById_BadRequest_IfEmptyUsername() throws Exception {
        UserRequest dto = new UserRequest("", "passS$123");

        mvc.perform(MockMvcRequestBuilders.request(HttpMethod.PUT, "/api/v1/user/" + user.getId())
                .accept(contentType)
                .content(objectMapper.writeValueAsString(dto))
                .contentType(contentType)
                .header(SecurityConstants.AUTH_HEADER_NAME, token))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void updateUserById_BadRequest_IfEmptyPassword() throws Exception {
        UserRequest dto = new UserRequest("", "");

        mvc.perform(MockMvcRequestBuilders.request(HttpMethod.PUT, "/api/v1/user/" + user.getId())
                .accept(contentType)
                .content(objectMapper.writeValueAsString(dto))
                .contentType(contentType)
                .header(SecurityConstants.AUTH_HEADER_NAME, token))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void updateUserById_BadRequest_IfWeakPassword() throws Exception {
        UserRequest dto = new UserRequest("user", "pass");

        mvc.perform(MockMvcRequestBuilders.request(HttpMethod.PUT, "/api/v1/user/" + user.getId())
                .accept(contentType)
                .content(objectMapper.writeValueAsString(dto))
                .contentType(contentType)
                .header(SecurityConstants.AUTH_HEADER_NAME, token))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void deleteUserById_Success_IfUserPresent() throws Exception {
        mvc.perform(MockMvcRequestBuilders.request(HttpMethod.DELETE, "/api/v1/user/" + user.getId())
                .accept(contentType)
                .contentType(contentType)
                .header(SecurityConstants.AUTH_HEADER_NAME, token))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(is(user.getId())));
    }
}
