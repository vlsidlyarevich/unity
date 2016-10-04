package com.github.vlsidlyarevich.unity.repository;


import com.github.vlsidlyarevich.unity.Application;
import com.github.vlsidlyarevich.unity.models.Name;
import com.github.vlsidlyarevich.unity.models.Speciality;
import com.github.vlsidlyarevich.unity.models.Worker;
import com.github.vlsidlyarevich.unity.models.WorkerProfile;
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

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringApplicationConfiguration(Application.class)
public class WorkerProfileRepositoryTest {

    @Autowired
    private WorkerProfileRepository workerProfileRepository;

    @Before
    public void cleanDb() {
        workerProfileRepository.deleteAll();
    }

    @Test
    public void findByIdTest() throws Exception {
        WorkerProfile saved = TestUtils.generateWorkerProfile();

        workerProfileRepository.save(saved);

        Assert.assertEquals(saved, workerProfileRepository.findById(saved.getId()));
    }

    @Test
    public void findByNameTest() throws Exception {
        WorkerProfile saved = TestUtils.generateWorkerProfile();
        Name name = new Name("Vladislav", "Sidlyarevich");
        saved.setName(name);

        workerProfileRepository.save(saved);

        Assert.assertEquals(saved, workerProfileRepository.findByName(name));
    }

    @Test
    public void findAllByAgeTest() throws Exception {
        WorkerProfile saved = TestUtils.generateWorkerProfile();
        saved.setAge(19);

        ArrayList<Worker> workers = new ArrayList<>();
        workers.add(saved);
        workerProfileRepository.save(saved);

        Assert.assertEquals(workers, workerProfileRepository.findAllByAge(19));
    }

    @Test
    public void findAllBySpecialityTest() throws Exception {
        WorkerProfile saved = TestUtils.generateWorkerProfile();
        saved.setSpeciality("SOFTWARE_ENGINEER");

        ArrayList<Worker> workers = new ArrayList<>();
        workers.add(saved);
        workerProfileRepository.save(saved);

        Assert.assertEquals(workers, workerProfileRepository.findAllBySpeciality(Speciality.SOFTWARE_ENGINEER));
    }
}
