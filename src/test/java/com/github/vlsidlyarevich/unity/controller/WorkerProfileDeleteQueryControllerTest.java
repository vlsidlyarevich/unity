package com.github.vlsidlyarevich.unity.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.vlsidlyarevich.unity.Application;
import com.github.vlsidlyarevich.unity.model.WorkerProfile;
import com.github.vlsidlyarevich.unity.repository.WorkerProfileRepository;
import com.github.vlsidlyarevich.unity.utils.TestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.WebApplicationContext;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * Created by vlad on 12/11/16.
 */
@WebAppConfiguration
@SpringApplicationConfiguration(Application.class)
public class WorkerProfileDeleteQueryControllerTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private WorkerProfileRepository workerProfileRepository;

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    private WorkerProfile savedWorker;

    @Test
    public void setupMvc() throws Exception {
        workerProfileRepository.deleteAll();
        this.mvc = webAppContextSetup(context).build();
    }


    @Test(dependsOnMethods = {"setupMvc"})
    public void deleteQueryTest() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        this.savedWorker = TestUtils.generateWorkerProfile();
        savedWorker = workerProfileRepository.save(savedWorker);

        Map<String, String> filters = new HashMap<>();
        filters.put("id", savedWorker.getId());

        mvc.perform(MockMvcRequestBuilders.request(HttpMethod.POST, "/api/workers/delete")
                .content(objectMapper.writeValueAsString(filters))
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().bytes("1".getBytes()));
    }

    @Test(dependsOnMethods = {"setupMvc"})
    public void deleteAllQueryTest() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        this.savedWorker = TestUtils.generateWorkerProfile();
        savedWorker = workerProfileRepository.save(savedWorker);

        Map<String, String> filters = new HashMap<>();
        filters.put("id", "all");

        mvc.perform(MockMvcRequestBuilders.request(HttpMethod.POST, "/api/workers/delete")
                .content(objectMapper.writeValueAsString(filters))
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().bytes("1".getBytes()));
    }

}