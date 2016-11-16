package com.github.vlsidlyarevich.unity.repository;


import com.github.vlsidlyarevich.unity.model.JobType;
import com.github.vlsidlyarevich.unity.model.Speciality;
import com.github.vlsidlyarevich.unity.model.Vacancy;
import org.springframework.data.neo4j.repository.GraphRepository;

import java.util.List;

public interface VacancyRepository extends GraphRepository<Vacancy> {

    List<Vacancy> findBySpeciality(Speciality speciality);

    List<Vacancy> findByJobType(JobType jobType);

    List<Vacancy> findByLocation(String location);
}
