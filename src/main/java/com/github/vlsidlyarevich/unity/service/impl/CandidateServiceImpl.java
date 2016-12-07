package com.github.vlsidlyarevich.unity.service.impl;

import com.github.vlsidlyarevich.unity.converter.factory.ConverterFactory;
import com.github.vlsidlyarevich.unity.dto.CandidateDTO;
import com.github.vlsidlyarevich.unity.exception.ServiceException;
import com.github.vlsidlyarevich.unity.model.Candidate;
import com.github.vlsidlyarevich.unity.model.Vacancy;
import com.github.vlsidlyarevich.unity.repository.CandidateRepository;
import com.github.vlsidlyarevich.unity.repository.VacancyRepository;
import com.github.vlsidlyarevich.unity.service.CandidateService;
import com.github.vlsidlyarevich.unity.service.StorageService;
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
    private ConverterFactory converterFactory;

    @Autowired
    private StorageService storageService;

    @Override
    public Candidate create(String vacancyId, CandidateDTO dto) {
        Candidate candidate = (Candidate) converterFactory.getConverter(CandidateDTO.class).convert(dto);
        candidate.setCreatedAt(String.valueOf(LocalDateTime.now()));

        Vacancy vacancy = vacancyRepository.findById(vacancyId);
        repository.save(candidate);
        vacancy.getCandidates().add(candidate);
        vacancyRepository.save(vacancy);

        return candidate;
    }

    @Override
    public Candidate find(String vacancyId, String candidateId) {
        try {
            return vacancyRepository.findById(vacancyId).getCandidates().stream()
                    .filter(s -> Objects.equals(s.getId(), candidateId)).findFirst().get();
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
    public Candidate update(String vacancyId, String candidateId, CandidateDTO dto) {
        Candidate candidate;
        if (repository.exists(candidateId)) {
            candidate = (Candidate) converterFactory.getConverter(CandidateDTO.class).convert(dto);
            if (!candidate.getImageId().equals(find(vacancyId, candidateId).getImageId())) {
                deleteImage(vacancyId, find(vacancyId, candidateId).getId());
            }

            candidate.setId(this.find(vacancyId, candidateId).getId());
            candidate.setUpdatedAt(String.valueOf(LocalDateTime.now()));
            this.delete(vacancyId, candidateId);

            Vacancy vacancy = vacancyRepository.findById(vacancyId);
            vacancy.getCandidates().add(candidate);
            repository.save(candidate);
            vacancyRepository.save(vacancy);

            return candidate;
        } else {
            candidate = (Candidate) converterFactory.getConverter(CandidateDTO.class).convert(dto);
            candidate.setCreatedAt(String.valueOf(LocalDateTime.now()));

            repository.save(candidate);
            Vacancy vacancy = vacancyRepository.findById(vacancyId);
            vacancy.getCandidates().add(candidate);
            vacancyRepository.save(vacancy);

            return candidate;
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
        Vacancy vacancy = vacancyRepository.findById(vacancyId);
        for (Candidate candidate : vacancy.getCandidates()) {
            if (candidate.getId().equals(candidateId)) {
                deleteImage(vacancyId, candidate.getId());
                vacancy.getCandidates().remove(candidate);
                repository.delete(candidate.getId());
                break;
            }
        }
        vacancyRepository.save(vacancy);
        return candidateId;
    }

    @Override
    public Integer deleteQuery(String vacancyId, Map<String, String> ids) {
        Integer deleteCounter = 0;

        if (ids.keySet().size() == 1 && ids.containsValue("all")) {
            deleteCounter = Math.toIntExact(repository.count());
            vacancyRepository.findById(vacancyId).getCandidates().clear();

            return deleteCounter;
        }

        for (Map.Entry<String, String> id : ids.entrySet()) {
            if (repository.exists(String.valueOf(id.getValue()))) {
                deleteImage(vacancyId, id.getValue());
                this.delete(vacancyId, id.getValue());
                deleteCounter++;
            }
        }
        return deleteCounter;
    }

    @Override
    public Integer deleteAll(String vacancyId) {
        Integer deleteCounter;
        try {
            Vacancy vacancy = vacancyRepository.findById(vacancyId);
            deleteCounter = Math.toIntExact(vacancy.getCandidates().size());
            vacancy.getCandidates().clear();
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), e.getCause(), this.getClass().getName());
        }

        return deleteCounter;
    }
}