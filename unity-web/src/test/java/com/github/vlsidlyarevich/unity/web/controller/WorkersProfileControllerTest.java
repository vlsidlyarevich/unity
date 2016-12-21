package com.github.vlsidlyarevich.unity.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.vlsidlyarevich.unity.db.service.WorkerProfileService;
import com.github.vlsidlyarevich.unity.web.TestUtils;
import com.github.vlsidlyarevich.unity.web.dto.WorkerProfileDTO;
import com.github.vlsidlyarevich.unity.db.model.WorkerProfile;
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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;


@RunWith(SpringRunner.class)
@SpringBootTest
public class WorkersProfileControllerTest {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private WorkerProfileService service;

    private MockMvc mvc;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
        service.deleteAll();
    }

    @After
    public void after() throws Exception {
        service.deleteAll();
    }

    @Test
    public void addWorkerTest() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        WorkerProfileDTO worker = TestUtils.generateWorkerProfileDTO();

        mvc.perform(MockMvcRequestBuilders.request(HttpMethod.POST, "/api/workers")
                .content(objectMapper.writeValueAsString(worker))
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void getWorkerByIdTest() throws Exception {
        WorkerProfile worker = TestUtils.generateWorkerProfile();
        WorkerProfile savedWorker = service.create(worker);

        mvc.perform(MockMvcRequestBuilders.request(HttpMethod.GET, "/api/workers/" + savedWorker.getId())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", is(savedWorker.getId())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name.firstName", is(savedWorker.getName().getFirstName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name.lastName", is(savedWorker.getName().getLastName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.age", is(savedWorker.getAge())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.skype", is(savedWorker.getSkype())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.gender", is(savedWorker.getGender().toString())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.phone", is(savedWorker.getPhone())));
    }

    @Test
    public void updateWorkerByIdTest() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        WorkerProfile worker = TestUtils.generateWorkerProfile();
        WorkerProfile savedWorker = service.create(worker);

        savedWorker.getName().setFirstName("Updated");
        worker.getName().setFirstName("Updated");
        savedWorker.setAge(20);
        worker.setAge(20);

        mvc.perform(MockMvcRequestBuilders.request(HttpMethod.PUT, "/api/workers/" + savedWorker.getId())
                .content(objectMapper.writeValueAsString(worker))
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", is(savedWorker.getId())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name.firstName", is(savedWorker.getName().getFirstName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name.lastName", is(savedWorker.getName().getLastName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.age", is(savedWorker.getAge())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.skype", is(savedWorker.getSkype())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.gender", is(savedWorker.getGender().toString())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.phone", is(savedWorker.getPhone())));
    }

    @Test
    public void getAllWorkerProfilesTest() throws Exception {
        WorkerProfile worker = TestUtils.generateWorkerProfile();
        WorkerProfile savedWorker = service.create(worker);

        mvc.perform(MockMvcRequestBuilders.request(HttpMethod.GET, "/api/workers")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].id", is(savedWorker.getId())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].name.firstName", is(savedWorker.getName().getFirstName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].name.lastName", is(savedWorker.getName().getLastName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].age", is(savedWorker.getAge())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].skype", is(savedWorker.getSkype())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].gender", is(savedWorker.getGender().toString())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].phone", is(savedWorker.getPhone())));
    }

    @Test
    public void deleteWorkerByIdTest() throws Exception {
        WorkerProfile worker = TestUtils.generateWorkerProfile();
        WorkerProfile savedWorker = service.create(worker);

        mvc.perform(MockMvcRequestBuilders.request(HttpMethod.DELETE, "/api/workers/" + savedWorker.getId())
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(String.valueOf(savedWorker.getId())));
    }
}
