package com.github.vlsidlyarevich.unity.db.service;

import com.github.vlsidlyarevich.unity.db.model.JobType;
import com.github.vlsidlyarevich.unity.db.model.Speciality;
import com.github.vlsidlyarevich.unity.db.model.Vacancy;
import com.github.vlsidlyarevich.unity.db.service.common.BaseService;

import java.util.List;


public interface VacancyService extends BaseService<Vacancy> {

    List<Vacancy> findBySpeciality(Speciality speciality);

    List<Vacancy> findByJobType(JobType jobType);
}
