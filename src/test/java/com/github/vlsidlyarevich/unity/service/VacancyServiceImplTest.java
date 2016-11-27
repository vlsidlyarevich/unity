package com.github.vlsidlyarevich.unity.service;

import com.github.vlsidlyarevich.unity.dto.VacancyDTO;
import com.github.vlsidlyarevich.unity.model.Vacancy;
import com.github.vlsidlyarevich.unity.repository.VacancyRepository;
import com.github.vlsidlyarevich.unity.utils.ModelUtils;
import com.github.vlsidlyarevich.unity.utils.TestUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


//TODO:// FIXME: 27/11/16
@RunWith(SpringRunner.class)
@SpringBootTest
public class VacancyServiceImplTest {

    @Autowired
    private VacancyService vacancyService;

    @Autowired
    private VacancyRepository vacancyRepository;

    @Before
    public void before() {
        vacancyRepository.deleteAll();
    }

    @After
    public void after() {
        vacancyRepository.deleteAll();
    }


    @Test
    public void createTest() throws Exception {
        VacancyDTO vacancyDTO = TestUtils.generateVacancyDTO();

        Vacancy saved = vacancyService.create(vacancyDTO);

        Assert.assertEquals(ModelUtils.convertToModel(vacancyDTO), saved);
        Assert.assertNotNull(saved.getCreatedAt());
    }

    @Test
    public void findByIdTest() throws Exception {
        VacancyDTO vacancyDTO = TestUtils.generateVacancyDTO();

        Vacancy saved = vacancyService.create(vacancyDTO);

        Assert.assertEquals(ModelUtils.convertToModel(vacancyDTO), vacancyService.find(saved.getId()));
        Assert.assertNotNull(saved.getCreatedAt());
    }

    @Test
    public void findAllTest() throws Exception {

    }

    @Test
    public void findBySpecialityTest() throws Exception {

    }

    @Test
    public void findByJobTypeTest() throws Exception {

    }

    @Test
    public void updateTest() throws Exception {

    }

    @Test
    public void deleteTest() throws Exception {

    }

    @Test
    public void deleteAllTest() throws Exception {

    }
}
