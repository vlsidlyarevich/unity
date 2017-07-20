package com.github.vlsidlyarevich.unity.web.controller;

import com.github.vlsidlyarevich.unity.Application;
import com.github.vlsidlyarevich.unity.db.domain.UserSocial;
import com.github.vlsidlyarevich.unity.db.repository.UserRepository;
import com.github.vlsidlyarevich.unity.db.repository.UserSocialRepository;
import com.github.vlsidlyarevich.unity.db.service.UserService;
import com.github.vlsidlyarevich.unity.web.dto.UserSocialDTO;
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

import static com.github.vlsidlyarevich.unity.TestUtils.createUserSocial;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class, PersistanceExceptionHandler.class,
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
                .header(SecurityConstants.AUTH_HEADER_NAME, token))
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
                .header(SecurityConstants.AUTH_HEADER_NAME, token))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void updateUserSocialData_Success_IfUserPresentAndValidDTO() throws Exception {
        UserSocialDTO dto = UserSocialDTO.fromDomain(userSocial);

        mvc.perform(MockMvcRequestBuilders.request(HttpMethod.PUT, "/api/v1/user/" + user.getId() + "/social")
                .accept(contentType)
                .content(objectMapper.writeValueAsString(dto))
                .contentType(contentType)
                .header(SecurityConstants.AUTH_HEADER_NAME, token))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void updateUserSocialData_Unauthorized_IfUserNotPresentAndValidDTO() throws Exception {
        UserSocialDTO dto = UserSocialDTO.fromDomain(userSocial);

        mvc.perform(MockMvcRequestBuilders.request(HttpMethod.PUT, "/api/v1/user/id0/social")
                .accept(contentType)
                .content(objectMapper.writeValueAsString(dto))
                .contentType(contentType)
                .header(SecurityConstants.AUTH_HEADER_NAME, token))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

    @Test
    public void deleteUserSocialDataByUserId_Success() throws Exception {
        userSocialRepository.save(userSocial);

        UserSocialDTO dto = UserSocialDTO.fromDomain(userSocial);

        mvc.perform(MockMvcRequestBuilders.request(HttpMethod.DELETE, "/api/v1/user/" + user.getId() + "/social")
                .accept(contentType)
                .content(objectMapper.writeValueAsString(dto))
                .contentType(contentType)
                .header(SecurityConstants.AUTH_HEADER_NAME, token))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(is(user.getId())));
    }
}
