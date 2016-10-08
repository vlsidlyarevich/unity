package com.github.vlsidlyarevich.unity.service;

import com.github.vlsidlyarevich.unity.Application;
import com.github.vlsidlyarevich.unity.models.Name;
import com.github.vlsidlyarevich.unity.models.WorkerProfile;
import com.github.vlsidlyarevich.unity.repository.WorkerProfileRepository;
import com.github.vlsidlyarevich.unity.service.impl.WorkerProfileServiceImpl;
import com.github.vlsidlyarevich.unity.utils.TestUtils;
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
    private WorkerProfileServiceImpl workerProfileServiceImpl;

    @Autowired
    private WorkerProfileRepository workerProfileRepository;

    @Before
    public void cleanDb() {
        workerProfileRepository.deleteAll();
    }


    @Test
    public void saveTest() throws Exception {
        WorkerProfile saved = TestUtils.generateWorkerProfile();

        workerProfileServiceImpl.save(saved);

        Assert.assertEquals(saved, workerProfileServiceImpl.findById(saved.getId()));
        Assert.assertNotNull(saved.getCreatedAt());
    }

    @Test
    public void findByIdTest() throws Exception {
        WorkerProfile saved = TestUtils.generateWorkerProfile();

        workerProfileServiceImpl.save(saved);

        Assert.assertEquals(saved, workerProfileServiceImpl.findById(saved.getId()));
    }

    @Test
    public void findByNameTest() throws Exception {
        WorkerProfile saved = TestUtils.generateWorkerProfile();
        Name name = new Name("Vladislav", "Sidlyarevich");
        saved.setName(name);

        workerProfileServiceImpl.save(saved);

        Assert.assertEquals(saved, workerProfileServiceImpl.findByName(name));
    }

    @Test
    public void findAllByAgeTest() throws Exception {
        WorkerProfile saved = TestUtils.generateWorkerProfile();
        saved.setAge(19);

        ArrayList<WorkerProfile> workers = new ArrayList<>();
        workers.add(saved);
        workerProfileServiceImpl.save(saved);

        Assert.assertEquals(workers, workerProfileServiceImpl.findAllByAge(19));
    }

    @Test
    public void findAllTest() throws Exception {
        WorkerProfile firstProfile = TestUtils.generateWorkerProfile();
        WorkerProfile secondProfile = TestUtils.generateWorkerProfile();

        ArrayList<WorkerProfile> savedWorkers = new ArrayList<>();
        savedWorkers.add(firstProfile);
        savedWorkers.add(secondProfile);
        workerProfileServiceImpl.save(firstProfile);
        workerProfileServiceImpl.save(secondProfile);

        assertThat(savedWorkers, is(workerProfileServiceImpl.findAll()));
    }
}
