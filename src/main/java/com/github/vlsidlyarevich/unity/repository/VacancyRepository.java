package com.github.vlsidlyarevich.unity.repository;

import com.github.vlsidlyarevich.unity.model.JobType;
import com.github.vlsidlyarevich.unity.model.Speciality;
import com.github.vlsidlyarevich.unity.model.Vacancy;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface VacancyRepository extends GraphRepository<Vacancy> {

    @Query("MATCH (vacancy:Vacancy)" +
            "RETURN vacancy")
    List<Vacancy> findAll();

    @Query("MATCH (vacancy:Vacancy)" +
            "WHERE id(vacancy)={vacancyId}" +
            "RETURN vacancy")
    Vacancy findOne(@Param("vacancyId") Long vacancyId);

    List<Vacancy> findBySpeciality(Speciality speciality);

    List<Vacancy> findByJobType(JobType jobType);

    List<Vacancy> findByLocation(String location);

    @Query("MATCH (vacancy:Vacancy)" +
            "WHERE id(vacancy)={vacancyId}" +
            "OPTIONAL MATCH (vacancy)-[r*]-(e) " +
            "FOREACH (rel IN r| DELETE rel)" +
            "DELETE e,vacancy")
    void delete(@Param("vacancyId") Long vacancyId);

    @Query("MATCH (vacancy:Vacancy)" +
            "OPTIONAL MATCH (vacancy)-[r*]-(e) " +
            "FOREACH (rel IN r| DELETE rel)" +
            "DELETE e,vacancy")
    void deleteAll();
}
