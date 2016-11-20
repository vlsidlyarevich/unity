package com.github.vlsidlyarevich.unity.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.vlsidlyarevich.unity.model.WorkerProfile;
import com.github.vlsidlyarevich.unity.repository.WorkerProfileRepository;
import com.github.vlsidlyarevich.unity.utils.TestUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;


@RunWith(SpringRunner.class)
@SpringBootTest
public class WorkerProfileDeleteQueryControllerTest {

    @Autowired
    private WorkerProfileRepository workerProfileRepository;

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    private WorkerProfile savedWorker;

    @Before
    public void setupMvc() throws Exception {
        workerProfileRepository.deleteAll();
        this.mvc = webAppContextSetup(context).build();
    }

    @Test
    public void deleteQueryTest() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        this.savedWorker = TestUtils.generateWorkerProfile();
        savedWorker = workerProfileRepository.save(savedWorker);

        Map<String, String> filters = new HashMap<>();
        filters.put("id", String.valueOf(savedWorker.getId()));

        mvc.perform(MockMvcRequestBuilders.request(HttpMethod.POST, "/api/workers/delete")
                .content(objectMapper.writeValueAsString(filters))
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().bytes("1".getBytes()));
    }

    @Test
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