package com.github.vlsidlyarevich.unity.services.db.service;

import com.github.vlsidlyarevich.unity.services.utils.TestUtils;
import com.github.vlsidlyarevich.unity.web.converter.ConverterFacade;
import com.github.vlsidlyarevich.unity.web.dto.WorkerProfileDTO;
import com.github.vlsidlyarevich.unity.db.model.model.Name;
import com.github.vlsidlyarevich.unity.db.model.model.WorkerProfile;
import org.apache.commons.collections4.CollectionUtils;
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

import static org.junit.Assert.assertTrue;


@RunWith(SpringRunner.class)
@SpringBootTest
public class WorkerProfileServiceImplTest {

    @Autowired
    private WorkerProfileService workerProfileService;

    @Autowired
    private ConverterFacade converter;

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

        Assert.assertEquals(converter.convert(dto), saved);
        Assert.assertNotNull(saved.getCreatedAt());
    }

    @Test
    public void findByIdTest() throws Exception {
        WorkerProfileDTO dto = TestUtils.generateWorkerProfileDTO();

        WorkerProfile saved = workerProfileService.create(dto);

        Assert.assertEquals(converter.convert(dto),
                workerProfileService.find(saved.getId()));
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

        Assert.assertEquals(converter.convert(dto),
                workerProfileService.findByName(name));
    }

    @Test
    public void findAllByAgeTest() throws Exception {
        WorkerProfileDTO dto = TestUtils.generateWorkerProfileDTO();
        dto.setAge(19);

        ArrayList<WorkerProfile> workers = new ArrayList<>();
        workers.add(converter.convert(dto));
        workerProfileService.create(dto);

        Assert.assertEquals(workers, workerProfileService.findAllByAge(19));
    }

    @Test
    public void findAllTest() throws Exception {
        WorkerProfileDTO firstDto = TestUtils.generateWorkerProfileDTO();
        WorkerProfileDTO secondDto = TestUtils.generateWorkerProfileDTO();

        ArrayList<WorkerProfile> savedWorkers = new ArrayList<>();
        savedWorkers.add(converter.convert(firstDto));
        savedWorkers.add(converter.convert(secondDto));
        workerProfileService.create(firstDto);
        workerProfileService.create(secondDto);

        assertTrue(CollectionUtils.isEqualCollection(savedWorkers, workerProfileService.findAll()));
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
