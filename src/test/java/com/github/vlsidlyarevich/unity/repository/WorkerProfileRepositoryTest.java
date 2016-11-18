package com.github.vlsidlyarevich.unity.repository;

import com.github.vlsidlyarevich.unity.Application;
import com.github.vlsidlyarevich.unity.model.Name;
import com.github.vlsidlyarevich.unity.model.Speciality;
import com.github.vlsidlyarevich.unity.model.Worker;
import com.github.vlsidlyarevich.unity.model.WorkerProfile;
import com.github.vlsidlyarevich.unity.utils.TestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;


@SpringApplicationConfiguration(Application.class)
public class WorkerProfileRepositoryTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private WorkerProfileRepository workerProfileRepository;

    @BeforeMethod
    public void before() {
        workerProfileRepository.deleteAll();
    }

    @AfterMethod
    public void after() {
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
