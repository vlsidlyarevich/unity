package com.github.vlsidlyarevich.unity.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.vlsidlyarevich.unity.db.model.WorkerProfile;
import com.github.vlsidlyarevich.unity.db.repository.WorkerProfileRepository;
import com.github.vlsidlyarevich.unity.web.TestUtils;
import org.junit.After;
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

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;


@RunWith(SpringRunner.class)
@SpringBootTest
public class WorkerProfileSearchControllerTest {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private WorkerProfileRepository workerProfileRepository;

    private MockMvc mvc;

    private WorkerProfile savedWorker;

    @Before
    public void setupMvc() throws Exception {
        workerProfileRepository.deleteAll();
        this.mvc = webAppContextSetup(context).build();
    }

    @After
    public void after() throws Exception {
        workerProfileRepository.deleteAll();
    }

    @Test
    public void getWorkersByFiltersTest() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        this.savedWorker = TestUtils.generateWorkerProfile();
        savedWorker = workerProfileRepository.save(savedWorker);

        Map<String, String> filters = new HashMap<>();
        filters.put("age", savedWorker.getAge().toString());
        filters.put("firstname", savedWorker.getName().getFirstName());

        mvc.perform(MockMvcRequestBuilders.request(HttpMethod.POST, "/api/workers/search")
                .content(objectMapper.writeValueAsString(filters))
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].name.firstName", is(savedWorker.getName().getFirstName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].name.lastName", is(savedWorker.getName().getLastName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].age", is(savedWorker.getAge())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].skype", is(savedWorker.getSkype())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].gender", is(savedWorker.getGender().name())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].phone", is(savedWorker.getPhone())));
    }

}