package com.github.vlsidlyarevich.unity.controller;

import com.github.vlsidlyarevich.unity.Application;
import com.github.vlsidlyarevich.unity.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
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
    public void getWorkersByFilters() throws Exception {


    }
}
