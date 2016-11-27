package com.github.vlsidlyarevich.unity.repository;

import com.github.vlsidlyarevich.unity.model.Candidate;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface CandidateRepository extends GraphRepository<Candidate> {

    @Query("MATCH (vacancy:Vacancy)-[r:HAS_CANDIDATE]->(candidate:Candidate)-[n:HAS_NAME]->(name:Name)" +
            "WHERE id(vacancy)={vacancyId}" +
            "DELETE r,n,candidate,name")
    void deleteAllInVacancy(@Param("vacancyId") Long vacancyId);

    @Query("MATCH (candidate:Candidate)-[r:HAS_NAME]->(name:Name)" +
            "DELETE r,candidate,name")
    void deleteAll();
}
