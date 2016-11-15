package com.github.vlsidlyarevich.unity.service;

import com.github.vlsidlyarevich.unity.Application;
import com.github.vlsidlyarevich.unity.dto.WorkerProfileDTO;
import com.github.vlsidlyarevich.unity.model.Name;
import com.github.vlsidlyarevich.unity.model.WorkerProfile;
import com.github.vlsidlyarevich.unity.repository.WorkerProfileRepository;
import com.github.vlsidlyarevich.unity.utils.ModelUtils;
import com.github.vlsidlyarevich.unity.utils.TestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


@SpringApplicationConfiguration(Application.class)
public class WorkerProfileServiceImplTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private WorkerProfileService workerProfileService;

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
    public void saveTest() throws Exception {
        WorkerProfileDTO dto = TestUtils.generateWorkerProfileDTO();

        WorkerProfile saved = workerProfileService.create(dto);

        Assert.assertEquals(ModelUtils.convertToModelProfile(dto), workerProfileService.find(saved.getId()));
        Assert.assertNotNull(saved.getCreatedAt());
    }

    @Test
    public void findByIdTest() throws Exception {
        WorkerProfileDTO dto = TestUtils.generateWorkerProfileDTO();

        WorkerProfile saved = workerProfileService.create(dto);

        Assert.assertEquals(ModelUtils.convertToModelProfile(dto), workerProfileService.find(saved.getId()));
    }

    @Test
    public void findByNameTest() throws Exception {
        WorkerProfileDTO dto = TestUtils.generateWorkerProfileDTO();
        Name name = new Name("Vladislav", "Sidlyarevich");
        dto.setName(name);

        workerProfileService.create(dto);

        Assert.assertEquals(ModelUtils.convertToModelProfile(dto), workerProfileService.findByName(name));
    }

    @Test
    public void findAllByAgeTest() throws Exception {
        WorkerProfileDTO dto = TestUtils.generateWorkerProfileDTO();
        dto.setAge(19);

        ArrayList<WorkerProfile> workers = new ArrayList<>();
        workers.add(ModelUtils.convertToModelProfile(dto));
        workerProfileService.create(dto);

        Assert.assertEquals(workers, workerProfileService.findAllByAge(19));
    }

    @Test
    public void findAllTest() throws Exception {
        WorkerProfileDTO firstDto = TestUtils.generateWorkerProfileDTO();
        WorkerProfileDTO secondDto = TestUtils.generateWorkerProfileDTO();

        ArrayList<WorkerProfile> savedWorkers = new ArrayList<>();
        savedWorkers.add(ModelUtils.convertToModelProfile(firstDto));
        savedWorkers.add(ModelUtils.convertToModelProfile(secondDto));
        workerProfileService.create(firstDto);
        workerProfileService.create(secondDto);

        assertThat(savedWorkers, is(workerProfileService.findAll()));
    }

    @Test
    public void deleteQueryAllTest() throws Exception {
        WorkerProfileDTO firstDto = TestUtils.generateWorkerProfileDTO();
        WorkerProfileDTO secondDto = TestUtils.generateWorkerProfileDTO();

        ArrayList<WorkerProfile> savedWorkers = new ArrayList<>();
        savedWorkers.add(ModelUtils.convertToModelProfile(firstDto));
        savedWorkers.add(ModelUtils.convertToModelProfile(secondDto));
        workerProfileService.create(firstDto);
        workerProfileService.create(secondDto);

        HashMap<String, String> map = new HashMap<>();
        map.put("id", "all");

        Assert.assertEquals(workerProfileService.deleteQuery(map), "2");
        Assert.assertEquals(workerProfileService.findAll().size(), 0);
    }

    @Test
    public void deleteQueryTest() throws Exception {
        WorkerProfileDTO firstDto = TestUtils.generateWorkerProfileDTO();
        WorkerProfileDTO secondDto = TestUtils.generateWorkerProfileDTO();

        ArrayList<WorkerProfile> savedWorkers = new ArrayList<>();
        savedWorkers.add(ModelUtils.convertToModelProfile(firstDto));
        savedWorkers.add(ModelUtils.convertToModelProfile(secondDto));

        HashMap<String, String> map = new HashMap<>();
        map.put("id1", workerProfileService.create(firstDto).getId());
        map.put("id2", workerProfileService.create(secondDto).getId());

        Assert.assertEquals(workerProfileService.deleteQuery(map), "2");
        Assert.assertEquals(workerProfileService.findAll().size(), 0);
    }
}
