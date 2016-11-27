package com.github.vlsidlyarevich.unity.service.impl;

import com.github.vlsidlyarevich.unity.dto.CandidateDTO;
import com.github.vlsidlyarevich.unity.model.Candidate;
import com.github.vlsidlyarevich.unity.repository.CandidateRepository;
import com.github.vlsidlyarevich.unity.repository.VacancyRepository;
import com.github.vlsidlyarevich.unity.service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class CandidateServiceImpl implements CandidateService {

    @Autowired
    private CandidateRepository repository;

    @Autowired
    private VacancyRepository vacancyRepository;

    @Override
    public Candidate create(CandidateDTO object) {
        return null;
    }

    @Override
    public Candidate find(Long id) {
        return null;
    }

    @Override
    public List<Candidate> findAll() {
        return null;
    }

    @Override
    public Long update(Long id, CandidateDTO object) {
        return null;
    }

    @Override
    public Long delete(Long id) {
        return null;
    }

    @Override
    public Integer deleteQuery(Map<String, String> ids) {
        return null;
    }

    @Override
    public Integer deleteAll() {
        return null;
    }
}
