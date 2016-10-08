package com.github.vlsidlyarevich.unity.service;

import com.github.vlsidlyarevich.unity.Application;
import com.github.vlsidlyarevich.unity.models.WorkerProfile;
import com.github.vlsidlyarevich.unity.repository.WorkerProfileRepository;
import com.github.vlsidlyarevich.unity.service.impl.WorkerProfileSearchServiceImpl;
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

import java.util.HashMap;
import java.util.List;

/**
 * Created by vlad on 02.10.16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringApplicationConfiguration(Application.class)
public class WorkerProfileSearchServiceImplTest {

    @Autowired
    private WorkerProfileSearchServiceImpl workerProfileSearchServiceImpl;

    @Autowired
    private WorkerProfileServiceImpl workerProfileServiceImpl;

    @Autowired
    private WorkerProfileRepository workerProfileRepository;

    @Before
    public void cleanDb() {
        workerProfileRepository.deleteAll();
    }


    @Test
    public void findByFiltersTest() {
        WorkerProfile workerProfile = TestUtils.generateWorkerProfile();
        workerProfileServiceImpl.save(workerProfile);

        HashMap<String, String> map = new HashMap<>();
        map.put("firstname", workerProfile.getName().getFirstName());
        map.put("skype", workerProfile.getSkype());
        List<WorkerProfile> workerProfiles = workerProfileSearchServiceImpl.find(map);
        Assert.assertEquals(workerProfile, workerProfiles.get(0));
    }
}
