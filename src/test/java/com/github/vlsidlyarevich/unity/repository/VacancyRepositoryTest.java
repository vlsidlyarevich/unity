package com.github.vlsidlyarevich.unity.repository;

import com.github.vlsidlyarevich.unity.Application;
import com.github.vlsidlyarevich.unity.model.Candidate;
import com.github.vlsidlyarevich.unity.model.Vacancy;
import com.github.vlsidlyarevich.unity.utils.TestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;


@SpringApplicationConfiguration(Application.class)
public class VacancyRepositoryTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private VacancyRepository vacancyRepository;

    @BeforeClass
    public void setUp() {
        vacancyRepository.deleteAll();
    }

    @AfterClass
    public void clear() {
        vacancyRepository.deleteAll();
    }

    private Vacancy vacancy;

    @Test
    public void saveTest() throws Exception {
        vacancy = TestUtils.generateVacancy();
        Candidate candidate = new Candidate();
        candidate.setGithubUrl("git");
        vacancy.addCandidate(candidate);

        vacancyRepository.save(vacancy);
        Assert.assertEquals(vacancyRepository.count(), 1L);
    }

    @Test(dependsOnMethods = "saveTest")
    public void findByIdTest() throws Exception {
        Assert.assertEquals(vacancy, vacancyRepository.findById(vacancy.getId()));
    }

    @Test(dependsOnMethods = "saveTest")
    public void findByLocationTest() throws Exception {
        Assert.assertEquals(new ArrayList<Vacancy>() {{
            add(vacancy);
        }}, vacancyRepository.findByLocation(vacancy.getLocation()));
    }

    @Test(dependsOnMethods = "saveTest")
    public void findByJobTypeTest() throws Exception {
        Assert.assertEquals(new ArrayList<Vacancy>() {{
                                add(vacancy);
                            }},
                vacancyRepository.findByJobType(vacancy.getJobType()));
    }

    @Test(dependsOnMethods = "saveTest")
    public void findAllBySpecialityTest() throws Exception {
        Assert.assertEquals(new ArrayList<Vacancy>() {{
            add(vacancy);
        }}, vacancyRepository.findBySpeciality(vacancy.getSpeciality()));
    }

    @Test
    public void deleteAllTest() throws Exception {
        vacancyRepository.deleteAll();
        Assert.assertEquals(0, vacancyRepository.count());
    }
}
