package com.github.vlsidlyarevich.unity.web.controller;

import com.github.vlsidlyarevich.unity.db.service.StorageService;
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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ImageControllerTest {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private StorageService storageService;

    private MockMvc mvc;

    @After
    public void after() throws Exception {
        storageService.deleteAll();
    }

    @Before
    public void setupMvc() throws Exception {
        storageService.deleteAll();
        this.mvc = webAppContextSetup(context).build();
    }

    @Test
    public void uploadImageTest() throws Exception {
        MockMultipartFile image = new MockMultipartFile("file", "Image.png", null, "content".getBytes());

        mvc.perform(MockMvcRequestBuilders.fileUpload("/api/v1/images/upload")
                .file(image)
                .contentType(MediaType.MULTIPART_FORM_DATA))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void getImageByIdTest() throws Exception {
        MockMultipartFile image = new MockMultipartFile("file", "Image.png", null, "content".getBytes());
        String imageId = storageService.store(image);

        mvc.perform(MockMvcRequestBuilders.request(HttpMethod.GET, "/api/v1/images/" + imageId)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().bytes(image.getBytes()));
    }

    @Test
    public void deleteImageTest() throws Exception {
        MockMultipartFile image = new MockMultipartFile("file", "Image.png", null, "content".getBytes());
        String imageId = storageService.store(image);

        mvc.perform(MockMvcRequestBuilders.request(HttpMethod.DELETE, "/api/v1/images/" + imageId)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
