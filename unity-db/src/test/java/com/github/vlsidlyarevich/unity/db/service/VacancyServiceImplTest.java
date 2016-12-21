package com.github.vlsidlyarevich.unity.db.service;

import com.github.vlsidlyarevich.unity.db.TestUtils;
import com.github.vlsidlyarevich.unity.db.repository.VacancyRepository;
import com.github.vlsidlyarevich.unity.db.model.Vacancy;
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
        Vacancy Vacancy = TestUtils.generateVacancy();

        Vacancy saved = vacancyService.create(Vacancy);

        Assert.assertEquals(Vacancy, saved);
        Assert.assertNotNull(saved.getCreatedAt());
    }

    @Test
    public void findByIdTest() throws Exception {
        Vacancy Vacancy = TestUtils.generateVacancy();

        Vacancy saved = vacancyService.create(Vacancy);

        Assert.assertEquals(Vacancy, vacancyService.find(saved.getId()));
    }

    @Test
    public void findAllTest() throws Exception {
        Vacancy Vacancy = TestUtils.generateVacancy();

        Vacancy saved = vacancyService.create(Vacancy);

        Assert.assertEquals(new ArrayList<Vacancy>() {{
                                add(Vacancy);
                            }},
                vacancyService.findAll());
    }

    @Test
    public void findBySpecialityTest() throws Exception {
        Vacancy Vacancy = TestUtils.generateVacancy();

        Vacancy saved = vacancyService.create(Vacancy);

        Assert.assertEquals(new ArrayList<Vacancy>() {{
                                add(Vacancy);
                            }},
                vacancyService.findBySpeciality(Vacancy.getSpeciality()));
    }

    @Test
    public void findByJobTypeTest() throws Exception {
        Vacancy Vacancy = TestUtils.generateVacancy();

        Vacancy saved = vacancyService.create(Vacancy);

        Assert.assertEquals(new ArrayList<Vacancy>() {{
                                add(Vacancy);
                            }},
                vacancyService.findByJobType(Vacancy.getJobType()));
    }

    @Test
    public void updateTest() throws Exception {
        Vacancy Vacancy = TestUtils.generateVacancy();

        Vacancy saved = vacancyService.create(Vacancy);

        Assert.assertEquals(Vacancy, saved);
        Assert.assertNotNull(saved.getCreatedAt());
    }

    @Test
    public void deleteQueryTest() throws Exception {
        Vacancy firstVacancy = TestUtils.generateVacancy();
        Vacancy secondVacancy = TestUtils.generateVacancy();

        HashMap<String, String> map = new HashMap<>();
        map.put("id1", String.valueOf(vacancyService.create(firstVacancy).getId()));
        map.put("id2", String.valueOf(vacancyService.create(secondVacancy).getId()));

        Assert.assertEquals(Integer.valueOf(2), vacancyService.deleteQuery(map));
        Assert.assertEquals(0, vacancyService.findAll().size());
    }

    @Test
    public void deleteQueryAllTest() throws Exception {
        Vacancy firstVacancy = TestUtils.generateVacancy();
        Vacancy secondVacancy = TestUtils.generateVacancy();

        vacancyService.create(firstVacancy).getId();
        vacancyService.create(secondVacancy).getId();

        HashMap<String, String> map = new HashMap<>();
        map.put("id", "all");

        Assert.assertEquals(Integer.valueOf(2), vacancyService.deleteQuery(map));
        Assert.assertEquals(0, vacancyService.findAll().size());
    }

    @Test
    public void deleteTest() throws Exception {
        Vacancy Vacancy = TestUtils.generateVacancy();

        Vacancy saved = vacancyService.create(Vacancy);

        vacancyService.delete(saved.getId());
        Assert.assertEquals(0, vacancyRepository.count());
    }

    @Test
    public void deleteAllTest() throws Exception {
        Vacancy Vacancy = TestUtils.generateVacancy();
        vacancyService.create(Vacancy);

        Vacancy vacancyDTO1 = TestUtils.generateVacancy();
        vacancyService.create(vacancyDTO1);

        vacancyService.deleteAll();
        Assert.assertEquals(0, vacancyRepository.count());
    }
}
