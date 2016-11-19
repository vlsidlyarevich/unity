package com.github.vlsidlyarevich.unity.repository;

import com.github.vlsidlyarevich.unity.model.JobType;
import com.github.vlsidlyarevich.unity.model.Speciality;
import com.github.vlsidlyarevich.unity.model.Vacancy;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface VacancyRepository extends GraphRepository<Vacancy> {

    @Query("MATCH (n) WHERE id(n)={0} RETURN n")
    Vacancy findById(Long id);

    List<Vacancy> findBySpeciality(Speciality speciality);

    List<Vacancy> findByJobType(JobType jobType);

    List<Vacancy> findByLocation(String location);

    @Query("MATCH (vacancy:Vacancy)-[r:CANDIDATE]->(candidate:Candidate) DELETE vacancy,r")
    void deleteAll();
}
