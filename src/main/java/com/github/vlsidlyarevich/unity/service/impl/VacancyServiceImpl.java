package com.github.vlsidlyarevich.unity.service.impl;

import com.github.vlsidlyarevich.unity.dto.VacancyDTO;
import com.github.vlsidlyarevich.unity.model.JobType;
import com.github.vlsidlyarevich.unity.model.Speciality;
import com.github.vlsidlyarevich.unity.model.Vacancy;
import com.github.vlsidlyarevich.unity.repository.VacancyRepository;
import com.github.vlsidlyarevich.unity.service.VacancyService;
import com.github.vlsidlyarevich.unity.utils.ModelUtils;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class VacancyServiceImpl implements VacancyService {

    @Autowired
    private VacancyRepository repository;

    @Override
    public Vacancy create(VacancyDTO dto) {
        Vacancy vacancy = ModelUtils.convertToModel(dto);
        vacancy.setCreatedAt(String.valueOf(LocalDateTime.now()));
        repository.save(vacancy);

        return vacancy;
    }

    @Override
    public Vacancy find(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Vacancy> findAll() {
        return Lists.newArrayList(repository.findAll().iterator());
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
    public Long update(Long id, VacancyDTO dto) {
        Vacancy vacancy = ModelUtils.convertToModel(dto);
        vacancy.setId(id);

        Vacancy saved = repository.findById(id);

        if (saved != null) {
            vacancy.setCreatedAt(saved.getCreatedAt());
            vacancy.setUpdatedAt(String.valueOf(LocalDateTime.now()));
        } else {
            vacancy.setCreatedAt(String.valueOf(LocalDateTime.now()));
        }
        repository.save(vacancy);
        return id;
    }

    @Override
    public Long delete(Long id) {
        repository.delete(id);
        return id;
    }

    @Override
    public Integer deleteAll() {
        Integer deleteCounter = Math.toIntExact(repository.count());
        repository.deleteAll();
        return deleteCounter;
    }
}
