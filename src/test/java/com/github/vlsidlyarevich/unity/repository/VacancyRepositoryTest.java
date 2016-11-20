package com.github.vlsidlyarevich.unity.repository;

import com.github.vlsidlyarevich.unity.model.Candidate;
import com.github.vlsidlyarevich.unity.model.Vacancy;
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


@RunWith(SpringRunner.class)
@SpringBootTest
public class VacancyRepositoryTest {

    @Autowired
    private VacancyRepository vacancyRepository;

    @Before
    public void setUp() {
        vacancyRepository.deleteAll();
    }

    @After
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

    @Test
    public void findByIdTest() throws Exception {
        vacancy = TestUtils.generateVacancy();
        Candidate candidate = new Candidate();
        candidate.setGithubUrl("git");
        vacancy.addCandidate(candidate);

        vacancyRepository.save(vacancy);

        Assert.assertEquals(vacancy, vacancyRepository.findById(vacancy.getId()));
    }

    @Test
    public void findByLocationTest() throws Exception {
        vacancy = TestUtils.generateVacancy();
        Candidate candidate = new Candidate();
        candidate.setGithubUrl("git");
        vacancy.addCandidate(candidate);

        vacancyRepository.save(vacancy);

        Assert.assertEquals(new ArrayList<Vacancy>() {{
            add(vacancy);
        }}, vacancyRepository.findByLocation(vacancy.getLocation()));
    }

    @Test
    public void findByJobTypeTest() throws Exception {
        vacancy = TestUtils.generateVacancy();
        Candidate candidate = new Candidate();
        candidate.setGithubUrl("git");
        vacancy.addCandidate(candidate);

        vacancyRepository.save(vacancy);

        Assert.assertEquals(new ArrayList<Vacancy>() {{
            add(vacancy);
        }}, vacancyRepository.findByJobType(vacancy.getJobType()));
    }

    @Test
    public void findAllBySpecialityTest() throws Exception {
        vacancy = TestUtils.generateVacancy();
        Candidate candidate = new Candidate();
        candidate.setGithubUrl("git");
        vacancy.addCandidate(candidate);

        vacancyRepository.save(vacancy);


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
