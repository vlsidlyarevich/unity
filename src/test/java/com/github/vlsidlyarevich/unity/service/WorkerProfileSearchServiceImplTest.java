package com.github.vlsidlyarevich.unity.service;

import com.github.vlsidlyarevich.unity.dto.WorkerProfileDTO;
import com.github.vlsidlyarevich.unity.model.WorkerProfile;
import com.github.vlsidlyarevich.unity.repository.WorkerProfileRepository;
import com.github.vlsidlyarevich.unity.service.mapper.ModelMapper;
import com.github.vlsidlyarevich.unity.utils.TestUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;

import static com.github.vlsidlyarevich.unity.service.mapper.ModelMapper.*;


@RunWith(SpringRunner.class)
@SpringBootTest
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
        map.put("skype", workerProfile.getSkype());
        List<WorkerProfile> workerProfiles = workerProfileSearchService.find(map);
        Assert.assertEquals(convertToModel(workerProfile), workerProfiles.get(0));
    }

    @Test
    public void findByFiltersExtendedTest() {
        WorkerProfileDTO workerProfile = TestUtils.generateWorkerProfileDTO();
        workerProfileService.create(workerProfile);

        HashMap<String, String> map = new HashMap<>();
        map.put("skype", workerProfile.getSkype());
        map.put("gender", workerProfile.getGender().name());
        map.put("speciality", workerProfile.getSpeciality().name());

        List<WorkerProfile> workerProfiles = workerProfileSearchService.find(map);
        Assert.assertEquals(convertToModel(workerProfile), workerProfiles.get(0));
    }
}
