package com.github.vlsidlyarevich.unity.db.service;

import com.github.vlsidlyarevich.unity.db.TestUtils;
import com.github.vlsidlyarevich.unity.db.model.Name;
import com.github.vlsidlyarevich.unity.db.model.WorkerProfile;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.assertTrue;


@RunWith(SpringRunner.class)
@SpringBootTest
public class WorkerProfileServiceImplTest {

    @Autowired
    private WorkerProfileService workerProfileService;

    @Before
    public void before() {
        workerProfileService.deleteAll();
    }

    @After
    public void after() {
        workerProfileService.deleteAll();
    }

    @Test
    public void saveTest() throws Exception {
        WorkerProfile workerProfile = TestUtils.generateWorkerProfile();

        WorkerProfile saved = workerProfileService.create(workerProfile);

        Assert.assertEquals(workerProfile, saved);
        Assert.assertNotNull(saved.getCreatedAt());
    }

    @Test
    public void findByIdTest() throws Exception {
        WorkerProfile WorkerProfile = TestUtils.generateWorkerProfile();

        WorkerProfile saved = workerProfileService.create(WorkerProfile);

        Assert.assertEquals((WorkerProfile),
                workerProfileService.find(saved.getId()));
    }

    @Test
    public void findByNameTest() throws Exception {
        WorkerProfile WorkerProfile = TestUtils.generateWorkerProfile();
        Name name = new Name("Vladislav", "Sidlyarevich");
        WorkerProfile.setName(name);

        WorkerProfile anotherDto = TestUtils.generateWorkerProfile();
        Name anotherName = new Name("Karina", "Sidlyarevich");
        anotherDto.setName(anotherName);

        workerProfileService.create(WorkerProfile);
        workerProfileService.create(anotherDto);

        Assert.assertEquals((WorkerProfile),
                workerProfileService.findByName(name));
    }

    @Test
    public void findAllByAgeTest() throws Exception {
        WorkerProfile WorkerProfile = TestUtils.generateWorkerProfile();
        WorkerProfile.setAge(19);

        ArrayList<WorkerProfile> workers = new ArrayList<>();
        workers.add((WorkerProfile));
        workerProfileService.create(WorkerProfile);

        Assert.assertEquals(workers, workerProfileService.findAllByAge(19));
    }

    @Test
    public void findAllTest() throws Exception {
        WorkerProfile firstWorker = TestUtils.generateWorkerProfile();
        WorkerProfile secondWorker = TestUtils.generateWorkerProfile();

        ArrayList<WorkerProfile> savedWorkers = new ArrayList<>();
        savedWorkers.add((firstWorker));
        savedWorkers.add((secondWorker));
        workerProfileService.create(firstWorker);
        workerProfileService.create(secondWorker);

        assertTrue(CollectionUtils.isEqualCollection(savedWorkers, workerProfileService.findAll()));
    }

    @Test
    public void deleteQueryAllTest() throws Exception {
        WorkerProfile firstWorker = TestUtils.generateWorkerProfile();
        WorkerProfile secondWorker = TestUtils.generateWorkerProfile();

        workerProfileService.create(firstWorker);
        workerProfileService.create(secondWorker);

        HashMap<String, String> map = new HashMap<>();
        map.put("id", "all");

        Assert.assertEquals(workerProfileService.deleteQuery(map), Integer.valueOf(2));
        Assert.assertEquals(workerProfileService.findAll().size(), 0);
    }

    @Test
    public void deleteQueryTest() throws Exception {
        WorkerProfile firstWorker = TestUtils.generateWorkerProfile();
        WorkerProfile secondWorker = TestUtils.generateWorkerProfile();

        HashMap<String, String> map = new HashMap<>();
        map.put("id1", String.valueOf(workerProfileService.create(firstWorker).getId()));
        map.put("id2", String.valueOf(workerProfileService.create(secondWorker).getId()));

        Assert.assertEquals(workerProfileService.deleteQuery(map), Integer.valueOf(2));
        Assert.assertEquals(workerProfileService.findAll().size(), 0);
    }
}
