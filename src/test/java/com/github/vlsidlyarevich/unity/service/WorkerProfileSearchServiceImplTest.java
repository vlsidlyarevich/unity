package com.github.vlsidlyarevich.unity.service;

import com.github.vlsidlyarevich.unity.Application;
import com.github.vlsidlyarevich.unity.dto.WorkerProfileDTO;
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
    private SearchService workerProfileSearchService;

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
    public void findByFiltersTest() {
        WorkerProfileDTO workerProfile = TestUtils.generateWorkerProfileDTO();
        workerProfileService.create(workerProfile);

        HashMap<String, String> map = new HashMap<>();
        map.put("firstname", workerProfile.getName().getFirstName());
        map.put("skype", workerProfile.getSkype());
        List<WorkerProfile> workerProfiles = workerProfileSearchService.find(map);
        Assert.assertEquals(ModelUtils.convertToModelProfile(workerProfile), workerProfiles.get(0));
    }
}
