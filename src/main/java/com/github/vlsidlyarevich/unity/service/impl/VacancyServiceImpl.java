package com.github.vlsidlyarevich.unity.service.impl;


import com.github.vlsidlyarevich.unity.dto.VacancyDTO;
import com.github.vlsidlyarevich.unity.model.JobType;
import com.github.vlsidlyarevich.unity.model.Speciality;
import com.github.vlsidlyarevich.unity.model.Vacancy;
import com.github.vlsidlyarevich.unity.repository.VacancyRepository;
import com.github.vlsidlyarevich.unity.service.VacancyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VacancyServiceImpl implements VacancyService {

    @Autowired
    private VacancyRepository vacancyRepository;

    @Override
    public Vacancy create(VacancyDTO object) {
        return null;
    }

    @Override
    public Vacancy find(Long id) {
        return null;
    }

    @Override
    public List<Vacancy> findAll() {
        return null;
    }

    @Override
    public Long update(Long id, VacancyDTO object) {
        return null;
    }

    @Override
    public Long delete(Long id) {
        return null;
    }

    @Override
    public List<Vacancy> findBySpeciality(Speciality speciality) {
        return null;
    }

    @Override
    public List<Vacancy> findByJobType(JobType jobType) {
        return null;
    }
}
