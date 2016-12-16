package com.github.vlsidlyarevich.unity.services.db.service.impl;

import com.github.vlsidlyarevich.unity.web.converter.ConverterFacade;
import com.github.vlsidlyarevich.unity.web.dto.VacancyDTO;
import com.github.vlsidlyarevich.unity.db.model.model.JobType;
import com.github.vlsidlyarevich.unity.db.model.model.Speciality;
import com.github.vlsidlyarevich.unity.db.model.model.Vacancy;
import com.github.vlsidlyarevich.unity.web.repository.VacancyRepository;
import com.github.vlsidlyarevich.unity.services.db.service.VacancyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;


@Service
public class VacancyServiceImpl implements VacancyService {

    @Autowired
    private VacancyRepository repository;

    @Autowired
    private ConverterFacade converter;

    @Override
    public Vacancy create(VacancyDTO dto) {
        Vacancy vacancy = converter.convert(dto);
        vacancy.setCreatedAt(String.valueOf(LocalDateTime.now()));
        repository.save(vacancy);

        return vacancy;
    }

    @Override
    public Vacancy find(String id) {
        return repository.findById(id);
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
    public Vacancy update(String id, VacancyDTO dto) {
        Vacancy vacancy = converter.convert(dto);
        vacancy.setId(id);

        Vacancy saved = repository.findById(id);

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
    public String delete(String id) {
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
            if (repository.exists(id.getValue())) {
                repository.delete(id.getValue());
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
