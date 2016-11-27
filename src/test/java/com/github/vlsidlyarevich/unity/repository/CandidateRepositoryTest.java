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


@RunWith(SpringRunner.class)
@SpringBootTest
public class CandidateRepositoryTest {

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private VacancyRepository vacancyRepository;

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

    @Test
    public void findByIdTest() throws Exception {
        Candidate candidate = TestUtils.generateCandidate();
        Long id = candidateRepository.save(candidate).getId();

        Assert.assertEquals(candidate, candidateRepository.findOne(id));
    }

    @Test
    public void deleteAllInVacancyTest() throws Exception {
        Vacancy vacancy = TestUtils.generateVacancy();
        vacancy.addCandidate(TestUtils.generateCandidate());
        vacancy.addCandidate(TestUtils.generateCandidate());

        Long vacancyId = vacancyRepository.save(vacancy).getId();

        candidateRepository.deleteAllInVacancy(vacancyId);
        Assert.assertEquals(0, candidateRepository.count());
    }

    @Test
    public void deleteAllTest() throws Exception {
        Candidate candidate = TestUtils.generateCandidate();
        candidateRepository.save(candidate);

        candidateRepository.deleteAll();

        Assert.assertEquals(0, candidateRepository.count());
    }
}
