package com.github.vlsidlyarevich.unity.repository;

import com.github.vlsidlyarevich.unity.model.JobType;
import com.github.vlsidlyarevich.unity.model.Speciality;
import com.github.vlsidlyarevich.unity.model.Vacancy;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface VacancyRepository extends MongoRepository<Vacancy, String> {

    Vacancy findById(String id);

    List<Vacancy> findBySpeciality(Speciality speciality);

    List<Vacancy> findByJobType(JobType jobType);

    List<Vacancy> findByLocation(String location);

}
