package com.github.vlsidlyarevich.unity.services.db.service;

import com.github.vlsidlyarevich.unity.services.db.service.common.BaseService;
import com.github.vlsidlyarevich.unity.web.dto.VacancyDTO;
import com.github.vlsidlyarevich.unity.db.model.model.JobType;
import com.github.vlsidlyarevich.unity.db.model.model.Speciality;
import com.github.vlsidlyarevich.unity.db.model.model.Vacancy;

import java.util.List;


public interface VacancyService extends BaseService<Vacancy, VacancyDTO> {

    List<Vacancy> findBySpeciality(Speciality speciality);

    List<Vacancy> findByJobType(JobType jobType);
}
