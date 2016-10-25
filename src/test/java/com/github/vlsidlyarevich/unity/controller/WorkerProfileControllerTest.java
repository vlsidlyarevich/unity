//package com.github.vlsidlyarevich.unity.controller;
//
//import com.github.vlsidlyarevich.unity.Application;
//import com.github.vlsidlyarevich.unity.dto.WorkerProfileDTO;
//import com.github.vlsidlyarevich.unity.repository.WorkerProfileRepository;
//import com.github.vlsidlyarevich.unity.service.WorkerProfileService;
//import com.github.vlsidlyarevich.unity.utils.TestUtils;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.SpringApplicationConfiguration;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.web.WebAppConfiguration;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//import org.springframework.web.context.WebApplicationContext;
//
//import static org.hamcrest.Matchers.hasSize;
//import static org.hamcrest.Matchers.is;
//import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
//
///**
// * Created by vladislav.sidlyarevich on 25/10/16.
// */
//@RunWith(SpringJUnit4ClassRunner.class)
//@WebAppConfiguration
//@SpringApplicationConfiguration(Application.class)
//public class WorkerProfileControllerTest {
//
//    @Autowired
//    private WebApplicationContext context;
//
//    @Autowired
//    private WorkerProfileService workerProfileService;
//
//    @Autowired
//    private WorkerProfileRepository workerProfileRepository;
//
//    private MockMvc mvc;
//
//    @Before
//    public void setup() throws Exception {
//        this.mvc = webAppContextSetup(context).build();
//    }
//
//    @After
//    public void after() throws Exception {
//        workerProfileRepository.deleteAll();
//    }
//
//    @Test
//    public void getAllWorkerProfilesTest() throws Exception {
//        WorkerProfileDTO worker = TestUtils.generateWorkerProfileDTO();
//        WorkerProfileDTO anotherWorker = TestUtils.generateWorkerProfileDTO();
//
//        String id = workerProfileService.create(worker).getId();
//        String id1 = workerProfileService.create(anotherWorker).getId();
//
//        mvc.perform(MockMvcRequestBuilders.request(HttpMethod.GET, "/api/workers")
//                .accept(MediaType.APPLICATION_JSON)
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(2)))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].id", is(id)))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].name.firstName", is(worker.getName().getFirstName())))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].name.secondName", is(worker.getName().getLastName())))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].age", is(worker.getAge())))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].skype", is(worker.getSkype())))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].gender", is(worker.getGender())))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].phone", is(worker.getPhone())))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].id", is(id1)))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].name.firstName", is(anotherWorker.getName().getFirstName())))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].name.secondName", is(anotherWorker.getName().getLastName())))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].age", is(anotherWorker.getAge())))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].skype", is(anotherWorker.getSkype())))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].gender", is(anotherWorker.getGender())))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].phone", is(anotherWorker.getPhone())));
//    }
//}
