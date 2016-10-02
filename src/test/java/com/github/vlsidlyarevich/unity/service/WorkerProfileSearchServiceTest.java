package com.github.vlsidlyarevich.unity.service;

import com.github.vlsidlyarevich.unity.Application;
import com.github.vlsidlyarevich.unity.models.WorkerProfile;
import com.github.vlsidlyarevich.unity.utils.TestUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.List;

/**
 * Created by vlad on 02.10.16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringApplicationConfiguration(Application.class)
public class WorkerProfileSearchServiceTest {

    @Autowired
    private WorkerProfileSearchService workerProfileSearchService;

    @Autowired
    private WorkerProfileService workerProfileService;

    @Test
    public void findByFiltersTest() {
        WorkerProfile workerProfile = TestUtils.generateWorkerProfile();
        workerProfileService.save(workerProfile);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("firstname", workerProfile.getName().getFirstName());
        map.add("skype", workerProfile.getSkype());
        List<WorkerProfile> workerProfiles = workerProfileSearchService.findByFilters(map);
        Assert.assertEquals(workerProfile, workerProfiles.get(0));
    }
}
