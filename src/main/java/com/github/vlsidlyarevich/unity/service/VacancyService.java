package com.github.vlsidlyarevich.unity.service;

import com.github.vlsidlyarevich.unity.dto.VacancyDTO;
import com.github.vlsidlyarevich.unity.model.JobType;
import com.github.vlsidlyarevich.unity.model.Speciality;
import com.github.vlsidlyarevich.unity.model.Vacancy;

import java.util.List;
import java.util.Map;


public interface VacancyService extends BaseService<Vacancy, VacancyDTO> {

    List<Vacancy> findBySpeciality(Speciality speciality);

    List<Vacancy> findByJobType(JobType jobType);

    Integer deleteQuery(Map<String, String> ids);

    Integer deleteAll();
}
