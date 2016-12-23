package com.github.vlsidlyarevich.unity.db.repository;

import com.github.vlsidlyarevich.unity.db.TestUtils;
import com.github.vlsidlyarevich.unity.db.model.Candidate;
import com.github.vlsidlyarevich.unity.db.model.Vacancy;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;


@RunWith(SpringRunner.class)
@SpringBootTest
public class VacancyRepositoryTest {

    @Autowired
    private VacancyRepository vacancyRepository;

    @Autowired
    private CandidateRepository candidateRepository;

    @Before
    public void setUp() {
        vacancyRepository.deleteAll();
        candidateRepository.deleteAll();
    }

    @After
    public void after() {
        vacancyRepository.deleteAll();
        candidateRepository.deleteAll();
    }

    private Vacancy vacancy;

    @Test
    public void saveTest() throws Exception {
        vacancy = TestUtils.generateVacancy();
        Candidate candidate = TestUtils.generateCandidate();
        vacancy.getCandidates().add(candidate);

        vacancyRepository.save(vacancy);
        Assert.assertEquals(1L, vacancyRepository.count());
    }

    @Test
    public void findByIdTest() throws Exception {
        vacancy = TestUtils.generateVacancy();
        Candidate candidate = TestUtils.generateCandidate();
        vacancy.getCandidates().add(candidate);

        vacancyRepository.save(vacancy);

        Assert.assertEquals(vacancy, vacancyRepository.findOne(vacancy.getId()));
    }

    @Test
    public void findByLocationTest() throws Exception {
        vacancy = TestUtils.generateVacancy();
        Candidate candidate = TestUtils.generateCandidate();
        vacancy.getCandidates().add(candidate);

        vacancyRepository.save(vacancy);

        Assert.assertEquals(new ArrayList<Vacancy>() {{
            add(vacancy);
        }}, vacancyRepository.findByLocation(vacancy.getLocation()));
    }

    @Test
    public void findByJobTypeTest() throws Exception {
        vacancy = TestUtils.generateVacancy();
        Candidate candidate = TestUtils.generateCandidate();
        vacancy.getCandidates().add(candidate);

        vacancyRepository.save(vacancy);

        Assert.assertEquals(new ArrayList<Vacancy>() {{
            add(vacancy);
        }}, vacancyRepository.findByJobType(vacancy.getJobType()));
    }

    @Test
    public void findAllBySpecialityTest() throws Exception {
        vacancy = TestUtils.generateVacancy();
        Candidate candidate = TestUtils.generateCandidate();
        vacancy.getCandidates().add(candidate);

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
