package com.github.vlsidlyarevich.unity.service;

import com.github.vlsidlyarevich.unity.dto.CandidateDTO;
import com.github.vlsidlyarevich.unity.model.Candidate;
import com.github.vlsidlyarevich.unity.model.Vacancy;
import com.github.vlsidlyarevich.unity.repository.CandidateRepository;
import com.github.vlsidlyarevich.unity.repository.VacancyRepository;
import com.github.vlsidlyarevich.unity.utils.TestUtils;
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

import static com.github.vlsidlyarevich.unity.utils.TestUtils.*;
import static org.junit.Assert.assertTrue;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CandidateServiceImplTest {

    @Autowired
    private CandidateService service;

    @Autowired
    private CandidateRepository repository;

    @Autowired
    private VacancyRepository vacancyRepository;

    @Before
    public void before()
    {
        repository.deleteAll();
        vacancyRepository.deleteAll();
    }

    @After
    public void after() {
        repository.deleteAll();
        vacancyRepository.deleteAll();
    }

    @Test
    public void createTest() throws Exception {
        Vacancy vacancy = generateVacancy();
        vacancyRepository.save(vacancy);

        CandidateDTO dto = generateCandidateDTO();
        service.create(vacancy.getId(), dto);

        Assert.assertEquals(1, repository.count());
        Assert.assertEquals(1, vacancyRepository.findOne(vacancy.getId()).getCandidates().size());
    }

    @Test
    public void findTest() throws Exception {
        Vacancy vacancy = generateVacancy();
        Candidate candidate = generateCandidate();
        vacancy.addCandidate(candidate);
        vacancyRepository.save(vacancy);

        Assert.assertEquals(candidate, service.find(vacancy.getId(), candidate.getId()));
    }

    @Test
    public void findAllTest() throws Exception {
        Vacancy vacancy = generateVacancy();
        Candidate candidate = generateCandidate();
        Candidate anotherCandidate = generateCandidate();
        vacancy.addCandidate(candidate);
        vacancy.addCandidate(anotherCandidate);
        vacancyRepository.save(vacancy);

        ArrayList<Candidate> candidates = new ArrayList<>();
        candidates.add(candidate);
        candidates.add(anotherCandidate);

        assertTrue(CollectionUtils.isEqualCollection(candidates, service.findAll()));
    }

    @Test
    public void findAllByVacancyTest() throws Exception {
        Vacancy vacancy = generateVacancy();
        Candidate candidate = generateCandidate();
        Candidate anotherCandidate = generateCandidate();
        vacancy.addCandidate(candidate);
        vacancy.addCandidate(anotherCandidate);
        vacancyRepository.save(vacancy);

        ArrayList<Candidate> candidates = new ArrayList<>();
        candidates.add(candidate);
        candidates.add(anotherCandidate);

        assertTrue(CollectionUtils.isEqualCollection(candidates, service.findAll(vacancy.getId())));
    }

    //TODO
    @Test
    public void updateTest() throws Exception {

    }

    //TODO
    @Test
    public void updateNotExistTest() throws Exception {

    }

    @Test
    public void deleteTest() throws Exception {
        Vacancy vacancy = generateVacancy();
        Candidate candidate = generateCandidate();
        vacancy.addCandidate(candidate);
        vacancyRepository.save(vacancy);

        service.delete(vacancy.getId(), candidate.getId());

        Assert.assertEquals(0, repository.count());
    }

    @Test
    public void deleteQueryTest() throws Exception {
        CandidateDTO firstDto = TestUtils.generateCandidateDTO();
        CandidateDTO secondDto = TestUtils.generateCandidateDTO();
        Vacancy vacancy = generateVacancy();
        vacancyRepository.save(vacancy);

        HashMap<String, String> map = new HashMap<>();
        map.put("id1", String.valueOf(service.create(vacancy.getId(), firstDto).getId()));
        map.put("id2", String.valueOf(service.create(vacancy.getId(), secondDto).getId()));

        Assert.assertEquals(service.deleteQuery(map), Integer.valueOf(2));
        Assert.assertEquals(service.findAll().size(), 0);
    }

    @Test
    public void deleteAllTest() throws Exception {
        Vacancy vacancy = generateVacancy();
        vacancy.addCandidate(generateCandidate());
        vacancy.addCandidate(generateCandidate());
        vacancy.addCandidate(generateCandidate());
        vacancyRepository.save(vacancy);

        Assert.assertEquals(Integer.valueOf(3), service.deleteAll());
        Assert.assertEquals(0, repository.count());
    }
}
