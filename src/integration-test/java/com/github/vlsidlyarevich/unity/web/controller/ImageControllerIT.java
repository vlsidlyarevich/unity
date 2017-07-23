package com.github.vlsidlyarevich.unity.web.controller;

import com.github.vlsidlyarevich.unity.Application;
import com.github.vlsidlyarevich.unity.db.service.StorageService;
import com.github.vlsidlyarevich.unity.db.service.UserService;
import com.github.vlsidlyarevich.unity.web.exception.handler.PersistanceExceptionHandler;
import com.github.vlsidlyarevich.unity.web.security.constant.SecurityConstants;
import com.github.vlsidlyarevich.unity.web.security.service.TokenService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.WebApplicationContext;

//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = {Application.class, PersistanceExceptionHandler.class})
//@WebAppConfiguration
public class ImageControllerIT extends AbstractControllerIT {

//    @Autowired
    private WebApplicationContext context;

//    @Autowired
    private StorageService storageService;

//    @Autowired
    private UserService userService;

//    @Autowired
    private TokenService tokenService;

//    @Before
    public void setupMvc() throws Exception {
        storageService.deleteAll();
        prepareTestContextWithUser(context);
        userService.create(user);
        this.token = tokenService.getToken(user.getUsername(), user.getPassword());
    }

//    @After
    public void after() throws Exception {
        storageService.deleteAll();
    }

//    @Test
    public void uploadImage_Created_IfValidImage() throws Exception {
        MockMultipartFile image = new MockMultipartFile("file", "Image.png", null, "content".getBytes());

        mvc.perform(MockMvcRequestBuilders.fileUpload("/api/v1/image")
                .file(image)
                .header(SecurityConstants.AUTH_HEADER_NAME, token)
                .contentType(MediaType.MULTIPART_FORM_DATA))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

//    @Test
    public void getImageById_NotFound_IfNotPresent() throws Exception {
        mvc.perform(MockMvcRequestBuilders.request(HttpMethod.GET, "/api/v1/image/" + "someId")
                .accept(MediaType.APPLICATION_JSON)
                .header(SecurityConstants.AUTH_HEADER_NAME, token)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

//    @Test
    public void getImageById_Success_IfPresent() throws Exception {
        MockMultipartFile image = new MockMultipartFile("file", "Image.png", null, "content".getBytes());
        String imageId = storageService.store(image);

        mvc.perform(MockMvcRequestBuilders.request(HttpMethod.GET, "/api/v1/image/" + imageId)
                .accept(MediaType.APPLICATION_JSON)
                .header(SecurityConstants.AUTH_HEADER_NAME, token)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().bytes(image.getBytes()));
    }

//    @Test
    public void deleteImage_Success() throws Exception {
        MockMultipartFile image = new MockMultipartFile("file", "Image.png", null, "content".getBytes());
        String imageId = storageService.store(image);

        mvc.perform(MockMvcRequestBuilders.request(HttpMethod.DELETE, "/api/v1/image/" + imageId)
                .accept(MediaType.APPLICATION_JSON)
                .header(SecurityConstants.AUTH_HEADER_NAME, token)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
