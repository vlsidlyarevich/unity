package com.github.vlsidlyarevich.unity.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.vlsidlyarevich.unity.Application;
import com.github.vlsidlyarevich.unity.dto.WorkerProfileDTO;
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
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * Created by vladislav.sidlyarevich on 25/10/16.
 */
@WebAppConfiguration
@SpringApplicationConfiguration(Application.class)
public class WorkersProfileControllerTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private WorkerProfileRepository workerProfileRepository;

    private MockMvc mvc;

    private WorkerProfileDTO worker;

    private WorkerProfile savedWorker;

    @AfterTest
    public void after() throws Exception {
        workerProfileRepository.deleteAll();
    }

    @Test
    public void setupMvc() throws Exception {
        workerProfileRepository.deleteAll();
        this.mvc = webAppContextSetup(context).build();
    }

    @Test(dependsOnMethods = {"setupMvc"})
    public void addWorkerTest() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        this.worker = TestUtils.generateWorkerProfileDTO();

        mvc.perform(MockMvcRequestBuilders.request(HttpMethod.POST, "/api/workers")
                .content(objectMapper.writeValueAsString(worker))
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(result -> this.savedWorker = objectMapper.readValue(result.getResponse()
                        .getContentAsString(), new TypeReference<WorkerProfile>() {
                }));
    }

    @Test(dependsOnMethods = {"addWorkerTest"})
    public void getWorkerById() throws Exception {
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

    @Test(dependsOnMethods = {"getWorkerById"})
    public void updateWorkerById() throws Exception {
        this.savedWorker.getName().setFirstName("Updated");
        this.worker.getName().setFirstName("Updated");
        this.savedWorker.setAge(20);
        this.worker.setAge(20);
        ObjectMapper objectMapper = new ObjectMapper();

        mvc.perform(MockMvcRequestBuilders.request(HttpMethod.PUT, "/api/workers/" + savedWorker.getId())
                .content(objectMapper.writeValueAsString(worker))
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(savedWorker.getId()));
    }

    @Test(dependsOnMethods = {"updateWorkerById"})
    public void getAllWorkerProfilesTest() throws Exception {
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

    @Test(dependsOnMethods = {"getAllWorkerProfilesTest"})
    public void deleteWorkerById() throws Exception {
        mvc.perform(MockMvcRequestBuilders.request(HttpMethod.DELETE, "/api/workers/" + savedWorker.getId())
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(savedWorker.getId()));
    }
}