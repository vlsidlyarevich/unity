package com.github.vlsidlyarevich.unity.controller;

import com.github.vlsidlyarevich.unity.Application;
import com.github.vlsidlyarevich.unity.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.WebApplicationContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * Created by vladislav.sidlyarevich on 03/11/16.
 */
@WebAppConfiguration
@SpringApplicationConfiguration(Application.class)
public class ImageControllerTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private StorageService storageService;

    private MockMvc mvc;

    private String imageId;

    private MockMultipartFile image;

    @AfterTest
    public void after() throws Exception {
        storageService.deleteAll();
    }

    @Test
    public void setupMvc() throws Exception {
        storageService.deleteAll();
        this.mvc = webAppContextSetup(context).build();
    }

    @Test(dependsOnMethods = {"setupMvc"})
    public void uploadImageTest() throws Exception {
        image = new MockMultipartFile("file", "Image.png", null, "content".getBytes());

        mvc.perform(MockMvcRequestBuilders.fileUpload("/api/images/upload")
                .file(image)
                .contentType(MediaType.MULTIPART_FORM_DATA))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(result -> this.imageId = result.getResponse()
                        .getContentAsString());
    }

    @Test(dependsOnMethods = {"uploadImageTest"})
    public void getImageByIdTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.request(HttpMethod.GET, "/api/images/" + imageId)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().bytes(image.getBytes()));
    }

    @Test(dependsOnMethods = {"getImageByIdTest"})
    public void deleteImageTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.request(HttpMethod.DELETE, "/api/images/" + imageId)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
