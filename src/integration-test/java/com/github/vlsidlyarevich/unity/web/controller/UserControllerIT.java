package com.github.vlsidlyarevich.unity.web.controller;

import com.github.vlsidlyarevich.unity.Application;
import com.github.vlsidlyarevich.unity.db.repository.UserRepository;
import com.github.vlsidlyarevich.unity.db.service.UserService;
import com.github.vlsidlyarevich.unity.web.exception.handler.SecurityExceptionHandler;
import com.github.vlsidlyarevich.unity.web.security.service.TokenService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class, SecurityExceptionHandler.class})
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
        init(context);
        userRepository.deleteAll();
        userService.create(user);
        this.token = tokenService.getToken(user.getUsername(), user.getPassword());
    }

    @Test
    public void getAllUsers_Success() {

    }

    public void getUserById_Success_IfUserPresent() {
    }

    public void getUserById_NoContent_IfUserNotPresent() {
    }

    public void addUser_Сreated_IfValidDTO() {
    }

    public void addUser_BadRequest_IfNotValidDTO() {
    }

    public void updateUserById_Success_IfValidDTO() {
    }

    public void updateUserById_Success_IfNotValidDTO() {
    }

    public void deleteUserById_Success() {
    }
}
