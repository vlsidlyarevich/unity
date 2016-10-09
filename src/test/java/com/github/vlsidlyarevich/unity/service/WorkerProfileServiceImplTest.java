package com.github.vlsidlyarevich.unity.service;

import com.github.vlsidlyarevich.unity.Application;
import com.github.vlsidlyarevich.unity.dto.WorkerProfileDTO;
import com.github.vlsidlyarevich.unity.model.Name;
import com.github.vlsidlyarevich.unity.model.WorkerProfile;
import com.github.vlsidlyarevich.unity.repository.WorkerProfileRepository;
import com.github.vlsidlyarevich.unity.utils.ModelUtils;
import com.github.vlsidlyarevich.unity.utils.TestUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

/**
 * Created by vlad on 04.10.16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringApplicationConfiguration(Application.class)
public class WorkerProfileServiceImplTest {

    @Autowired
    private WorkerProfileService workerProfileService;

    @Autowired
    private WorkerProfileRepository workerProfileRepository;

    @Before
    public void before() {
        workerProfileRepository.deleteAll();
    }

    @After
    public void after() {
        workerProfileRepository.deleteAll();
    }

    @Test
    public void saveTest() throws Exception {
        WorkerProfileDTO dto = TestUtils.generateWorkerProfileDTO();

        WorkerProfile saved = workerProfileService.create(dto);

        Assert.assertEquals(ModelUtils.convertToModelProfile(dto), workerProfileService.find(saved.getId()));
        Assert.assertNotNull(saved.getCreatedAt());
    }

    @Test
    public void findByIdTest() throws Exception {
        WorkerProfileDTO dto = TestUtils.generateWorkerProfileDTO();

        WorkerProfile saved = workerProfileService.create(dto);

        Assert.assertEquals(ModelUtils.convertToModelProfile(dto), workerProfileService.find(saved.getId()));
    }

    @Test
    public void findByNameTest() throws Exception {
        WorkerProfileDTO dto = TestUtils.generateWorkerProfileDTO();
        Name name = new Name("Vladislav", "Sidlyarevich");
        dto.setName(name);

        workerProfileService.create(dto);

        Assert.assertEquals(ModelUtils.convertToModelProfile(dto), workerProfileService.findByName(name));
    }

    @Test
    public void findAllByAgeTest() throws Exception {
        WorkerProfileDTO dto = TestUtils.generateWorkerProfileDTO();
        dto.setAge(19);

        ArrayList<WorkerProfile> workers = new ArrayList<>();
        workers.add(ModelUtils.convertToModelProfile(dto));
        workerProfileService.create(dto);

        Assert.assertEquals(workers, workerProfileService.findAllByAge(19));
    }

    @Test
    public void findAllTest() throws Exception {
        WorkerProfileDTO firstDto = TestUtils.generateWorkerProfileDTO();
        WorkerProfileDTO secondDto = TestUtils.generateWorkerProfileDTO();

        ArrayList<WorkerProfile> savedWorkers = new ArrayList<>();
        savedWorkers.add(ModelUtils.convertToModelProfile(firstDto));
        savedWorkers.add(ModelUtils.convertToModelProfile(secondDto));
        workerProfileService.create(firstDto);
        workerProfileService.create(secondDto);

        assertThat(savedWorkers, is(workerProfileService.findAll()));
    }
}
