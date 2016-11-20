package com.github.vlsidlyarevich.unity.repository;

import com.github.vlsidlyarevich.unity.model.Name;
import com.github.vlsidlyarevich.unity.model.Speciality;
import com.github.vlsidlyarevich.unity.model.Worker;
import com.github.vlsidlyarevich.unity.model.WorkerProfile;
import com.github.vlsidlyarevich.unity.utils.TestUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;


@RunWith(SpringRunner.class)
@SpringBootTest
public class WorkerProfileRepositoryTest {

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

        Assert.assertEquals(saved, workerProfileRepository.findByName(name.getFirstName(), name.getLastName()));
    }

    @Test
    public void findAllByAgeTest() throws Exception {
        WorkerProfile saved = TestUtils.generateWorkerProfile();
        saved.setAge(19);

        workerProfileRepository.save(saved);

        Assert.assertEquals(new ArrayList<Worker>() {{
            add(saved);
        }}, workerProfileRepository.findAllByAge(19));
    }

    @Test
    public void findAllBySpecialityTest() throws Exception {
        WorkerProfile saved = TestUtils.generateWorkerProfile();
        saved.setSpeciality("SOFTWARE_ENGINEER");

        ArrayList<Worker> workers = new ArrayList<>();
        workers.add(saved);
        workerProfileRepository.save(saved);

        Assert.assertEquals(new ArrayList<Worker>() {{
            add(saved);
        }}, workerProfileRepository.findAllBySpeciality(Speciality.SOFTWARE_ENGINEER));
    }
}
