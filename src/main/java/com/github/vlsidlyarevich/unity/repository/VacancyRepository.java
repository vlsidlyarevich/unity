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

    List<Vacancy> findBySpeciality(Speciality speciality);

    List<Vacancy> findByJobType(JobType jobType);

    List<Vacancy> findByLocation(String location);

    @Query("MATCH (vacancy:Vacancy)-[r:HAS_CANDIDATE]->(candidate:Candidate)-[n:HAS_NAME]->(name:Name)" +
            "DELETE r,n,candidate,vacancy,name")
    void deleteAll();
}
