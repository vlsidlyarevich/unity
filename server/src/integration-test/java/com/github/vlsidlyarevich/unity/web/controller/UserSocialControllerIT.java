package com.github.vlsidlyarevich.unity.web.controller;

import com.github.vlsidlyarevich.unity.Application;
import com.github.vlsidlyarevich.unity.domain.model.UserSocial;
import com.github.vlsidlyarevich.unity.domain.repository.UserRepository;
import com.github.vlsidlyarevich.unity.domain.repository.UserSocialRepository;
import com.github.vlsidlyarevich.unity.domain.service.UserService;
import com.github.vlsidlyarevich.unity.web.dto.user.UserSocialRequest;
import com.github.vlsidlyarevich.unity.web.exception.handler.PersistenceExceptionHandler;
import com.github.vlsidlyarevich.unity.web.exception.handler.SecurityExceptionHandler;
import com.github.vlsidlyarevich.unity.web.security.service.TokenService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.WebApplicationContext;

import static com.github.vlsidlyarevich.unity.TestUtils.createUserSocial;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class, PersistenceExceptionHandler.class,
        SecurityExceptionHandler.class})
@WebAppConfiguration
public class UserSocialControllerIT extends AbstractControllerIT {

    private UserSocial userSocial;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserSocialRepository userSocialRepository;

    @Autowired
    private TokenService tokenService;

    @Value("security.token.header.name")
    private String authHeaderName;

    @Before
    public void setUp() {
        prepareTestContextWithUser(context);

        userRepository.deleteAll();
        userSocial = createUserSocial();

        userRepository.deleteAll();
        userService.create(user);

        this.token = tokenService.getToken(user.getUsername(), user.getPassword());
    }

    @Test
    public void getUserSocialData_Success_IfPresent() throws Exception {
        userSocial.setUserId(user.getId());
        userSocialRepository.save(userSocial);

        mvc.perform(MockMvcRequestBuilders.request(HttpMethod.GET, "/api/v1/user/" + user.getId() + "/social")
                .accept(contentType)
                .contentType(contentType)
                .header(authHeaderName, token))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", is(userSocial.getId())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email", is(userSocial.getEmail())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName", is(userSocial.getFirstName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName", is(userSocial.getLastName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.skype", is(userSocial.getSkype())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.userId", is(userSocial.getUserId())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.image", is(userSocial.getImage())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.createdAt", is(userSocial.getCreatedAt().getTime())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.updatedAt", is(userSocial.getUpdatedAt().getTime())));
    }

    @Test
    public void getUserSocialData_NotFound_IfNotPresent() throws Exception {
        mvc.perform(MockMvcRequestBuilders.request(HttpMethod.GET, "/api/v1/user/" + user.getId() + "/social")
                .accept(contentType)
                .contentType(contentType)
                .header(authHeaderName, token))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void updateUserSocialData_Success_IfUserPresentAndValidDTO() throws Exception {
        UserSocialRequest dto = UserSocialRequest.fromDomain(userSocial);

        mvc.perform(MockMvcRequestBuilders.request(HttpMethod.PUT, "/api/v1/user/" + user.getId() + "/social")
                .accept(contentType)
                .content(objectMapper.writeValueAsString(dto))
                .contentType(contentType)
                .header(authHeaderName, token))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void updateUserSocialData_Unauthorized_IfUserNotPresentAndValidDTO() throws Exception {
        UserSocialRequest dto = UserSocialRequest.fromDomain(userSocial);

        mvc.perform(MockMvcRequestBuilders.request(HttpMethod.PUT, "/api/v1/user/id0/social")
                .accept(contentType)
                .content(objectMapper.writeValueAsString(dto))
                .contentType(contentType)
                .header(authHeaderName, token))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

    @Test
    public void deleteUserSocialDataByUserId_Success() throws Exception {
        userSocialRepository.save(userSocial);

        UserSocialRequest dto = UserSocialRequest.fromDomain(userSocial);

        mvc.perform(MockMvcRequestBuilders.request(HttpMethod.DELETE, "/api/v1/user/" + user.getId() + "/social")
                .accept(contentType)
                .content(objectMapper.writeValueAsString(dto))
                .contentType(contentType)
                .header(authHeaderName, token))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(is(user.getId())));
    }
}
