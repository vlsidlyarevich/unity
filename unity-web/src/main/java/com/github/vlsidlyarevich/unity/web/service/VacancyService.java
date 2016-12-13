package com.github.vlsidlyarevich.unity.web.service;

import com.github.vlsidlyarevich.unity.web.dto.VacancyDTO;
import com.github.vlsidlyarevich.unity.web.model.JobType;
import com.github.vlsidlyarevich.unity.web.model.Speciality;
import com.github.vlsidlyarevich.unity.web.model.Vacancy;
import com.github.vlsidlyarevich.unity.web.service.common.BaseService;

import java.util.List;


public interface VacancyService extends BaseService<Vacancy, VacancyDTO> {

    List<Vacancy> findBySpeciality(Speciality speciality);

    List<Vacancy> findByJobType(JobType jobType);
}
