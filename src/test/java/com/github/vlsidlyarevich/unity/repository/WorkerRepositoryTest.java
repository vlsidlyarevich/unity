package com.github.vlsidlyarevich.unity.repository;

import com.github.vlsidlyarevich.unity.Application;
import com.github.vlsidlyarevich.unity.domain.Worker;
import com.github.vlsidlyarevich.unity.models.Name;
import com.github.vlsidlyarevich.unity.models.Speciality;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;

/**
 * Created by vlad on 18.09.16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringApplicationConfiguration(Application.class)
public class WorkerRepositoryTest {

    @Autowired
    private WorkerRepository workerRepository;

    @Before
    public void cleanDb() {
        workerRepository.deleteAll();
    }


    @Test
    public void findByNameTest() {
        Worker saved = new Worker();
        Name name = new Name("Vladislav", "Sidlyarevich");
        saved.setName(name);

        this.workerRepository.save(saved);

        Assert.assertEquals(saved, workerRepository.findByName(name));
    }

    @Test
    public void findByAgeTest() {
        Worker saved = new Worker();
        saved.setName(new Name("Vladislav", "Sidlyarevich"));
        saved.setAge(19);

        ArrayList<Worker> workers = new ArrayList<>();
        workers.add(saved);
        this.workerRepository.save(saved);

        Assert.assertEquals(workers, workerRepository.findAllByAge(19));
    }

    @Test
    public void findBySpecialityTest() {
        Worker saved = new Worker();
        saved.setName(new Name("Vladislav", "Sidlyarevich"));
        saved.setAge(19);
        saved.setSpeciality("SOFTWARE_ENGINEER");

        ArrayList<Worker> workers = new ArrayList<>();
        workers.add(saved);
        this.workerRepository.save(saved);

        Assert.assertEquals(workers, workerRepository.findAllBySpeciality(Speciality.SOFTWARE_ENGINEER));
    }
}
