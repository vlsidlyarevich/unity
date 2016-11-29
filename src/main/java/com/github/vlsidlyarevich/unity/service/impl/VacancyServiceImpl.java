package com.github.vlsidlyarevich.unity.service.impl;

import com.github.vlsidlyarevich.unity.dto.VacancyDTO;
import com.github.vlsidlyarevich.unity.model.JobType;
import com.github.vlsidlyarevich.unity.model.Speciality;
import com.github.vlsidlyarevich.unity.model.Vacancy;
import com.github.vlsidlyarevich.unity.repository.VacancyRepository;
import com.github.vlsidlyarevich.unity.service.VacancyService;
import com.github.vlsidlyarevich.unity.service.mapper.ModelMapper;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static com.github.vlsidlyarevich.unity.service.mapper.ModelMapper.*;


@Service
public class VacancyServiceImpl implements VacancyService {

    @Autowired
    private VacancyRepository repository;

    @Override
    public Vacancy create(VacancyDTO dto) {
        Vacancy vacancy = convertToModel(dto);
        vacancy.setCreatedAt(String.valueOf(LocalDateTime.now()));
        repository.save(vacancy);

        return vacancy;
    }

    @Override
    public Vacancy find(Long id) {
        return repository.findOne(id);
    }

    @Override
    public List<Vacancy> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Vacancy> findBySpeciality(Speciality speciality) {
        return repository.findBySpeciality(speciality);
    }

    @Override
    public List<Vacancy> findByJobType(JobType jobType) {
        return repository.findByJobType(jobType);
    }

    @Override
    public Vacancy update(Long id, VacancyDTO dto) {
        Vacancy vacancy = convertToModel(dto);
        vacancy.setId(id);

        Vacancy saved = repository.findOne(id);

        if (saved != null) {
            vacancy.setCandidates(saved.getCandidates());
            vacancy.setCreatedAt(saved.getCreatedAt());
            vacancy.setUpdatedAt(String.valueOf(LocalDateTime.now()));
        } else {
            vacancy.setCreatedAt(String.valueOf(LocalDateTime.now()));
        }
        repository.save(vacancy);
        return vacancy;
    }

    @Override
    public Long delete(Long id) {
        repository.delete(id);
        return id;
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
