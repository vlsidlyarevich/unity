package com.github.vlsidlyarevich.unity.repository;


import com.github.vlsidlyarevich.unity.Application;
import com.github.vlsidlyarevich.unity.models.Name;
import com.github.vlsidlyarevich.unity.models.Speciality;
import com.github.vlsidlyarevich.unity.models.Worker;
import com.github.vlsidlyarevich.unity.models.WorkerProfile;
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
    public void findByNameTest() {
        WorkerProfile saved = new WorkerProfile();
        Name name = new Name("Vladislav", "Sidlyarevich");
        saved.setName(name);

        this.workerProfileRepository.save(saved);

        Assert.assertEquals(saved, workerProfileRepository.findByName(name));
    }

    @Test
    public void findByAgeTest() {
        WorkerProfile saved = new WorkerProfile();
        saved.setName(new Name("Vladislav", "Sidlyarevich"));
        saved.setAge(19);

        ArrayList<Worker> workers = new ArrayList<>();
        workers.add(saved);
        this.workerProfileRepository.save(saved);

        Assert.assertEquals(workers, workerProfileRepository.findAllByAge(19));
    }

    @Test
    public void findBySpecialityTest() {
        WorkerProfile saved = new WorkerProfile();
        saved.setName(new Name("Vladislav", "Sidlyarevich"));
        saved.setAge(19);
        saved.setSpeciality("SOFTWARE_ENGINEER");

        ArrayList<Worker> workers = new ArrayList<>();
        workers.add(saved);
        this.workerProfileRepository.save(saved);

        Assert.assertEquals(workers, workerProfileRepository.findAllBySpeciality(Speciality.SOFTWARE_ENGINEER));
    }
}
