package com.github.vlsidlyarevich.unity.web.service;

import com.github.vlsidlyarevich.unity.web.converter.ConverterFacade;
import com.github.vlsidlyarevich.unity.web.dto.CandidateDTO;
import com.github.vlsidlyarevich.unity.web.model.Candidate;
import com.github.vlsidlyarevich.unity.web.model.Vacancy;
import com.github.vlsidlyarevich.unity.web.repository.CandidateRepository;
import com.github.vlsidlyarevich.unity.web.repository.VacancyRepository;
import com.github.vlsidlyarevich.unity.web.utils.TestUtils;
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

import static com.github.vlsidlyarevich.unity.web.utils.TestUtils.*;
import static org.junit.Assert.assertEquals;
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

    @Autowired
    private ConverterFacade converter;

    @Before
    public void before() {
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
        vacancy.getCandidates().add(candidate);
        vacancyRepository.save(vacancy);

        Assert.assertEquals(candidate, service.find(vacancy.getId(), candidate.getId()));
    }

    @Test
    public void findAllTest() throws Exception {
        Vacancy vacancy = generateVacancy();
        CandidateDTO candidate = generateCandidateDTO();
        CandidateDTO anotherCandidate = generateCandidateDTO();
        vacancyRepository.save(vacancy);
        service.create(vacancy.getId(), candidate);
        service.create(vacancy.getId(), anotherCandidate);

        assertEquals(2, service.findAll(vacancy.getId()).size());
    }

    @Test
    public void findAllByVacancyTest() throws Exception {
        Vacancy vacancy = generateVacancy();
        Candidate candidate = generateCandidate();
        Candidate anotherCandidate = generateCandidate();
        vacancy.getCandidates().add(candidate);
        vacancy.getCandidates().add(anotherCandidate);
        vacancyRepository.save(vacancy);

        ArrayList<Candidate> candidates = new ArrayList<>();
        candidates.add(candidate);
        candidates.add(anotherCandidate);

        assertTrue(CollectionUtils.isEqualCollection(candidates, service.findAll(vacancy.getId())));
    }

    @Test
    public void updateTest() throws Exception {
        Vacancy vacancy = generateVacancy();
        vacancyRepository.save(vacancy);
        CandidateDTO candidate = generateCandidateDTO();
        String savedCandidateId = service.create(vacancy.getId(), candidate).getId();

        CandidateDTO updated = generateCandidateDTO();
        Candidate updatedModel = converter.convert(updated);

        String id = (service.update(vacancy.getId(), savedCandidateId, updated)).getId();
        updatedModel.setId(id);

        Assert.assertEquals(updatedModel, service.find(vacancy.getId(), updatedModel.getId()));
    }

    @Test
    public void updateNotExistTest() throws Exception {
        Vacancy vacancy = generateVacancy();
        vacancyRepository.save(vacancy);

        CandidateDTO updated = generateCandidateDTO();
        Candidate updatedModel = converter.convert(updated);

        String id = (service.update(vacancy.getId(), "", updated)).getId();
        updatedModel.setId(id);

        Assert.assertEquals(updatedModel, service.find(vacancy.getId(), updatedModel.getId()));
    }

    @Test
    public void deleteTest() throws Exception {
        Vacancy vacancy = generateVacancy();
        CandidateDTO candidate = generateCandidateDTO();
        vacancyRepository.save(vacancy);
        String candidateId = service.create(vacancy.getId(), candidate).getId();

        service.delete(vacancy.getId(), candidateId);

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

        Assert.assertEquals(service.deleteQuery(vacancy.getId(), map), Integer.valueOf(2));
        Assert.assertEquals(service.findAll(vacancy.getId()).size(), 0);
    }


    @Test
    public void deleteQueryAllTest() throws Exception {
        CandidateDTO firstDto = TestUtils.generateCandidateDTO();
        CandidateDTO secondDto = TestUtils.generateCandidateDTO();
        Vacancy vacancy = generateVacancy();
        vacancyRepository.save(vacancy);
        service.create(vacancy.getId(), firstDto);
        service.create(vacancy.getId(), secondDto);

        HashMap<String, String> map = new HashMap<>();
        map.put("id1", "all");

        Assert.assertEquals(service.deleteQuery(vacancy.getId(), map), Integer.valueOf(2));
        Assert.assertEquals(service.findAll(vacancy.getId()).size(), 0);
    }

    @Test
    public void deleteAllTest() throws Exception {
        Vacancy vacancy = generateVacancy();
        vacancy.getCandidates().add(generateCandidate());
        vacancy.getCandidates().add(generateCandidate());
        vacancy.getCandidates().add(generateCandidate());
        vacancyRepository.save(vacancy);

        Assert.assertEquals(Integer.valueOf(3), service.deleteAll(vacancy.getId()));
    }
}
