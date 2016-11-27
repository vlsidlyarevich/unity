package com.github.vlsidlyarevich.unity.service;

import com.github.vlsidlyarevich.unity.dto.WorkerProfileDTO;
import com.github.vlsidlyarevich.unity.model.Name;
import com.github.vlsidlyarevich.unity.model.WorkerProfile;
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

import java.util.ArrayList;
import java.util.HashMap;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
public class WorkerProfileServiceImplTest {

    @Autowired
    private WorkerProfileService workerProfileService;

    @Before
    public void before() {
        workerProfileService.deleteAll();
    }

    @After
    public void after() {
        workerProfileService.deleteAll();
    }

    @Test
    public void saveTest() throws Exception {
        WorkerProfileDTO dto = TestUtils.generateWorkerProfileDTO();

        WorkerProfile saved = workerProfileService.create(dto);

        Assert.assertEquals(ModelMapper.convertToModel(dto), saved);
        Assert.assertNotNull(saved.getCreatedAt());
    }

    @Test
    public void findByIdTest() throws Exception {
        WorkerProfileDTO dto = TestUtils.generateWorkerProfileDTO();

        WorkerProfile saved = workerProfileService.create(dto);

        Assert.assertEquals(ModelMapper.convertToModel(dto), workerProfileService.find(saved.getId()));
    }

    @Test
    public void findByNameTest() throws Exception {
        WorkerProfileDTO dto = TestUtils.generateWorkerProfileDTO();
        Name name = new Name("Vladislav", "Sidlyarevich");
        dto.setName(name);

        WorkerProfileDTO anotherDto = TestUtils.generateWorkerProfileDTO();
        Name anotherName = new Name("Karina", "Sidlyarevich");
        anotherDto.setName(anotherName);

        workerProfileService.create(dto);
        workerProfileService.create(anotherDto);

        Assert.assertEquals(ModelMapper.convertToModel(dto), workerProfileService.findByName(name));
    }

    @Test
    public void findAllByAgeTest() throws Exception {
        WorkerProfileDTO dto = TestUtils.generateWorkerProfileDTO();
        dto.setAge(19);

        ArrayList<WorkerProfile> workers = new ArrayList<>();
        workers.add(ModelMapper.convertToModel(dto));
        workerProfileService.create(dto);

        Assert.assertEquals(workers, workerProfileService.findAllByAge(19));
    }

    @Test
    public void findAllTest() throws Exception {
        WorkerProfileDTO firstDto = TestUtils.generateWorkerProfileDTO();
        WorkerProfileDTO secondDto = TestUtils.generateWorkerProfileDTO();

        ArrayList<WorkerProfile> savedWorkers = new ArrayList<>();
        savedWorkers.add(ModelMapper.convertToModel(firstDto));
        savedWorkers.add(ModelMapper.convertToModel(secondDto));
        workerProfileService.create(firstDto);
        workerProfileService.create(secondDto);

        assertThat(savedWorkers, is(workerProfileService.findAll()));
    }

    @Test
    public void deleteQueryAllTest() throws Exception {
        WorkerProfileDTO firstDto = TestUtils.generateWorkerProfileDTO();
        WorkerProfileDTO secondDto = TestUtils.generateWorkerProfileDTO();

        workerProfileService.create(firstDto);
        workerProfileService.create(secondDto);

        HashMap<String, String> map = new HashMap<>();
        map.put("id", "all");

        Assert.assertEquals(workerProfileService.deleteQuery(map), Integer.valueOf(2));
        Assert.assertEquals(workerProfileService.findAll().size(), 0);
    }

    @Test
    public void deleteQueryTest() throws Exception {
        WorkerProfileDTO firstDto = TestUtils.generateWorkerProfileDTO();
        WorkerProfileDTO secondDto = TestUtils.generateWorkerProfileDTO();

        HashMap<String, String> map = new HashMap<>();
        map.put("id1", String.valueOf(workerProfileService.create(firstDto).getId()));
        map.put("id2", String.valueOf(workerProfileService.create(secondDto).getId()));

        Assert.assertEquals(workerProfileService.deleteQuery(map), Integer.valueOf(2));
        Assert.assertEquals(workerProfileService.findAll().size(), 0);
    }
}
