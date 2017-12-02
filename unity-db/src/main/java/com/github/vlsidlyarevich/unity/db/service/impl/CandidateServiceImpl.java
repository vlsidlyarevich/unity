package com.github.vlsidlyarevich.unity.db.service.impl;

import com.github.vlsidlyarevich.unity.db.exception.ServiceException;
import com.github.vlsidlyarevich.unity.db.model.Candidate;
import com.github.vlsidlyarevich.unity.db.model.Vacancy;
import com.github.vlsidlyarevich.unity.db.repository.CandidateRepository;
import com.github.vlsidlyarevich.unity.db.repository.VacancyRepository;
import com.github.vlsidlyarevich.unity.db.service.CandidateService;
import com.github.vlsidlyarevich.unity.db.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;


@Slf4j
@Service
public class CandidateServiceImpl implements CandidateService {

    @Autowired
    private CandidateRepository repository;

    @Autowired
    private VacancyRepository vacancyRepository;

    @Autowired
    private StorageService storageService;

    @Override
    public Candidate create(String vacancyId, Candidate candidate) {
        Vacancy vacancy = vacancyRepository.findById(vacancyId);
        candidate.setVacancy(vacancy);

        return repository.save(candidate);
    }

    @Override
    public Candidate find(String vacancyId, String candidateId) {
        try {
            return repository.findByVacancyAndId(vacancyRepository.findById(vacancyId), candidateId);
        } catch (NoSuchElementException e) {
            log.warn("Candidate not found with vacancy id: " + vacancyId + "\nAnd candidate id: " + candidateId);
//            throw new ServiceException(e.getMessage(), e.getCause(), this.getClass().getName());
        }
        return null;
    }

    @Override
    public List<Candidate> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Candidate> findAll(String vacancyId) {
        return vacancyRepository.findById(vacancyId).getCandidates();
    }

    @Override
    public Candidate update(String vacancyId, String candidateId, Candidate candidate) {
        if (repository.exists(candidateId)) {
            if (!candidate.getImageId().equals(find(vacancyId, candidateId).getImageId())) {
                deleteImage(vacancyId, find(vacancyId, candidateId).getId());
            }

            candidate.setId(this.find(vacancyId, candidateId).getId());
            this.delete(vacancyId, candidateId);

            Vacancy vacancy = vacancyRepository.findById(vacancyId);
            candidate.setVacancy(vacancy);

            return repository.save(candidate);
        } else {
            return this.create(vacancyId, candidate);
        }
    }

    @Override
    public String deleteImage(String vacancyId, String candidateId) {
        String imageId = find(vacancyId, candidateId).getImageId();
        if (storageService.exists(imageId)) {
            storageService.delete(imageId);
        }
        return imageId;
    }

    @Override
    public String delete(String vacancyId, String candidateId) {
        deleteImage(vacancyId, candidateId);
        Vacancy vacancy = vacancyRepository.findById(vacancyId);

        vacancy.getCandidates().remove(repository.findOne(candidateId));
        vacancyRepository.save(vacancy);

        return candidateId;
    }

    @Override
    public Integer deleteQuery(String vacancyId, Map<String, String> ids) {
        Integer deleteCounter = 0;

        if (ids.keySet().size() == 1 && ids.containsValue("all")) {
            deleteCounter = vacancyRepository.findById(vacancyId).getCandidates().size();
            vacancyRepository.findById(vacancyId).getCandidates()
                    .forEach(candidate -> delete(vacancyId, candidate.getId()));

            return deleteCounter;
        }

        for (Map.Entry<String, String> id : ids.entrySet()) {
            if (repository.exists(String.valueOf(id.getValue()))) {
                this.delete(vacancyId, id.getValue());
                deleteCounter++;
            }
        }
        return deleteCounter;
    }

    @Override
    public Integer deleteAll(String vacancyId) {
        final Integer[] deleteCounter = new Integer[1];
        try {
            Vacancy vacancy = vacancyRepository.findById(vacancyId);
            deleteCounter[0] = Math.toIntExact(vacancy.getCandidates().size());
            vacancy.getCandidates().forEach(candidate -> {
                deleteCounter[0]++;
                delete(vacancyId, candidate.getId());
            });
            vacancyRepository.save(vacancy);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), e.getCause(), this.getClass().getName());
        }

        return deleteCounter[0];
    }
}