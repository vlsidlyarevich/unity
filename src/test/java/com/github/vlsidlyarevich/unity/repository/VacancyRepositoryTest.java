package com.github.vlsidlyarevich.unity.repository;

import com.github.vlsidlyarevich.unity.Application;
import com.github.vlsidlyarevich.unity.model.Candidate;
import com.github.vlsidlyarevich.unity.model.Vacancy;
import com.github.vlsidlyarevich.unity.utils.TestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Transactional
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

    @Test
    public void saveTest() throws Exception {
        Vacancy vacancy = TestUtils.generateVacancy();
        Candidate candidate = new Candidate();
        candidate.setGithubUrl("git");
        vacancy.addCandidate(candidate);

        vacancyRepository.save(vacancy);
        Assert.assertEquals(vacancyRepository.findOne(vacancy.getId()), vacancy);
        Assert.assertEquals(vacancyRepository.count(), 1L);
    }

}
