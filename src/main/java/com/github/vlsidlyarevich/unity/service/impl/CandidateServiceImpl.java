package com.github.vlsidlyarevich.unity.service.impl;

import com.github.vlsidlyarevich.unity.dto.CandidateDTO;
import com.github.vlsidlyarevich.unity.model.Candidate;
import com.github.vlsidlyarevich.unity.model.Vacancy;
import com.github.vlsidlyarevich.unity.repository.CandidateRepository;
import com.github.vlsidlyarevich.unity.repository.VacancyRepository;
import com.github.vlsidlyarevich.unity.service.CandidateService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static com.github.vlsidlyarevich.unity.service.mapper.ModelMapper.convertToModel;


@Service
public class CandidateServiceImpl implements CandidateService {

    @Autowired
    private CandidateRepository repository;

    @Autowired
    private VacancyRepository vacancyRepository;

    @Override
    public Candidate create(Long vacancyId, CandidateDTO dto) {
        Candidate candidate = convertToModel(dto);
        candidate.setCreatedAt(String.valueOf(LocalDateTime.now()));
        candidate.getName().setCreatedAt(String.valueOf(LocalDateTime.now()));

        Vacancy vacancy = vacancyRepository.findOne(vacancyId);
        vacancy.getCandidates().add(candidate);
        vacancyRepository.save(vacancy);

        return candidate;
    }

    @Override
    public Candidate find(Long vacancyId, Long candidateId) {
        return repository.findByVacancy(vacancyId, candidateId);
    }

    @Override
    public List<Candidate> findAll() {
        return Lists.newArrayList(repository.findAll().iterator());
    }

    @Override
    public List<Candidate> findAll(Long vacancyId) {
        return Lists.newArrayList(vacancyRepository.findOne(vacancyId).getCandidates());
    }

    @Override
    public Candidate update(Long vacancyId, Long candidateId, CandidateDTO dto) {
        Candidate candidate;
        if (repository.exists(candidateId)) {
            candidate = repository.findByVacancy(vacancyId, candidateId);
            candidate.setHrSkype(dto.getHrSkype());
            candidate.setLinkedInUrl(dto.getLinkedInUrl());
            candidate.setGender(dto.getGender());
            candidate.setImageId(dto.getImageId());
            candidate.setName(dto.getName());
            candidate.setGithubUrl(dto.getGithubUrl());
            candidate.setSkype(dto.getSkype());
            candidate.setAge(dto.getAge());
            candidate.setBirthday(dto.getBirthday());
            candidate.setUpdatedAt(String.valueOf(LocalDateTime.now()));
            candidate.getName().setUpdatedAt(String.valueOf(LocalDateTime.now()));

            return repository.save(candidate);
        } else {
            candidate = convertToModel(dto);
            candidate.setCreatedAt(String.valueOf(LocalDateTime.now()));
            candidate.getName().setCreatedAt(String.valueOf(LocalDateTime.now()));

            Vacancy vacancy = vacancyRepository.findOne(vacancyId);
            vacancy.getCandidates().add(candidate);
            vacancyRepository.save(vacancy);

            return candidate;
        }
    }

    @Override
    public Long delete(Long vacancyId, Long candidateId) {
        repository.deleteInVacancy(vacancyId, candidateId);
        return candidateId;
    }

    @Override
    public Integer deleteQuery(Map<String, String> ids) {
        Integer deleteCounter = 0;

        if (ids.keySet().size() == 1 && ids.containsValue("all")) {
            deleteCounter = Math.toIntExact(repository.count());
            repository.deleteAll();

            return deleteCounter;
        }

        for (Map.Entry<String, String> id : ids.entrySet()) {
            if (repository.exists(Long.valueOf(id.getValue()))) {
                repository.delete(Long.valueOf(id.getValue()));
                deleteCounter++;
            }
        }
        return deleteCounter;
    }

    @Override
    public Integer deleteAll() {
        Integer deleteCounter = Math.toIntExact(repository.count());
        repository.deleteAll();
        return deleteCounter;
    }
}