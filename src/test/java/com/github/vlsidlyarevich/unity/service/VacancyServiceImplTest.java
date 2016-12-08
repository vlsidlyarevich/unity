package com.github.vlsidlyarevich.unity.service;

import com.github.vlsidlyarevich.unity.converter.ConverterFacade;
import com.github.vlsidlyarevich.unity.dto.VacancyDTO;
import com.github.vlsidlyarevich.unity.model.Vacancy;
import com.github.vlsidlyarevich.unity.repository.VacancyRepository;
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


@RunWith(SpringRunner.class)
@SpringBootTest
public class VacancyServiceImplTest {

    @Autowired
    private VacancyService vacancyService;

    @Autowired
    private VacancyRepository vacancyRepository;

    @Autowired
    private ConverterFacade converter;

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

        Assert.assertEquals(converter.convert(vacancyDTO), saved);
        Assert.assertNotNull(saved.getCreatedAt());
    }

    @Test
    public void findByIdTest() throws Exception {
        VacancyDTO vacancyDTO = TestUtils.generateVacancyDTO();

        Vacancy saved = vacancyService.create(vacancyDTO);

        Assert.assertEquals(converter.convert(vacancyDTO),
                vacancyService.find(saved.getId()));
    }

    @Test
    public void findAllTest() throws Exception {
        VacancyDTO vacancyDTO = TestUtils.generateVacancyDTO();

        Vacancy saved = vacancyService.create(vacancyDTO);

        Assert.assertEquals(new ArrayList<Vacancy>() {{
                                add(converter.convert(vacancyDTO));
                            }},
                vacancyService.findAll());
    }

    @Test
    public void findBySpecialityTest() throws Exception {
        VacancyDTO vacancyDTO = TestUtils.generateVacancyDTO();

        Vacancy saved = vacancyService.create(vacancyDTO);

        Assert.assertEquals(new ArrayList<Vacancy>() {{
                                add(converter.convert(vacancyDTO));
                            }},
                vacancyService.findBySpeciality(vacancyDTO.getSpeciality()));
    }

    @Test
    public void findByJobTypeTest() throws Exception {
        VacancyDTO vacancyDTO = TestUtils.generateVacancyDTO();

        Vacancy saved = vacancyService.create(vacancyDTO);

        Assert.assertEquals(new ArrayList<Vacancy>() {{
                                add(converter.convert(vacancyDTO));
                            }},
                vacancyService.findByJobType(vacancyDTO.getJobType()));
    }

    @Test
    public void updateTest() throws Exception {
        VacancyDTO vacancyDTO = TestUtils.generateVacancyDTO();

        Vacancy saved = vacancyService.create(vacancyDTO);

        Assert.assertEquals(converter.convert(vacancyDTO), saved);
        Assert.assertNotNull(saved.getCreatedAt());
    }

    @Test
    public void deleteQueryTest() throws Exception {
        VacancyDTO firstDto = TestUtils.generateVacancyDTO();
        VacancyDTO secondDto = TestUtils.generateVacancyDTO();

        HashMap<String, String> map = new HashMap<>();
        map.put("id1", String.valueOf(vacancyService.create(firstDto).getId()));
        map.put("id2", String.valueOf(vacancyService.create(secondDto).getId()));

        Assert.assertEquals(Integer.valueOf(2), vacancyService.deleteQuery(map));
        Assert.assertEquals(0, vacancyService.findAll().size());
    }

    @Test
    public void deleteQueryAllTest() throws Exception {
        VacancyDTO firstDto = TestUtils.generateVacancyDTO();
        VacancyDTO secondDto = TestUtils.generateVacancyDTO();

        vacancyService.create(firstDto).getId();
        vacancyService.create(secondDto).getId();

        HashMap<String, String> map = new HashMap<>();
        map.put("id", "all");

        Assert.assertEquals(Integer.valueOf(2), vacancyService.deleteQuery(map));
        Assert.assertEquals(0, vacancyService.findAll().size());
    }

    @Test
    public void deleteTest() throws Exception {
        VacancyDTO vacancyDTO = TestUtils.generateVacancyDTO();

        Vacancy saved = vacancyService.create(vacancyDTO);

        vacancyService.delete(saved.getId());
        Assert.assertEquals(0, vacancyRepository.count());
    }

    @Test
    public void deleteAllTest() throws Exception {
        VacancyDTO vacancyDTO = TestUtils.generateVacancyDTO();
        vacancyService.create(vacancyDTO);

        VacancyDTO vacancyDTO1 = TestUtils.generateVacancyDTO();
        vacancyService.create(vacancyDTO1);

        vacancyService.deleteAll();
        Assert.assertEquals(0, vacancyRepository.count());
    }
}
