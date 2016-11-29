package com.github.vlsidlyarevich.unity.repository;

import com.github.vlsidlyarevich.unity.model.Candidate;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CandidateRepository extends GraphRepository<Candidate> {

    @Query("MATCH (vacancy:Vacancy)-[:HAS_CANDIDATE]->(candidate:Candidate)" +
            "WHERE id(vacancy)={vacancyId} AND id(candidate)={candidateId}" +
            "RETURN candidate")
    Candidate findByVacancy(@Param("vacancyId") Long vacancyId, @Param("candidateId") Long candidateId);

    @Query("MATCH (vacancy:Vacancy)-[:HAS_CANDIDATE]->(candidate:Candidate)" +
            "WHERE id(vacancy)={vacancyId}" +
            "RETURN candidate")
    List<Candidate> findAllInVacancy(@Param("vacancyId") Long vacancyId);

    @Query("MATCH (vacancy:Vacancy)-[r:HAS_CANDIDATE]->(candidate:Candidate)," +
            "(candidate:Candidate)-[n:HAS_NAME]->(name:Name)" +
            "WHERE id(vacancy)={vacancyId}" +
            "DELETE n,name,r,candidate")
    void deleteAllInVacancy(@Param("vacancyId") Long vacancyId);

    @Query("MATCH (vacancy:Vacancy)-[r:HAS_CANDIDATE]->(candidate:Candidate),(candidate:Candidate)-[n:HAS_NAME]->(name:Name)" +
            "WHERE id(vacancy)={vacancyId} AND id(candidate)={candidateId}" +
            "DELETE n,name,r,candidate")
    void deleteInVacancy(@Param("vacancyId") Long vacancyId, @Param("candidateId") Long candidateId);

    @Query("MATCH (candidate:Candidate)-[r:HAS_NAME]->(name:Name)" +
            "OPTIONAL MATCH (vacancy:Vacancy)-[n:HAS_CANDIDATE]->(candidate:Candidate)-[r:HAS_NAME]->(name:Name)" +
            "DELETE r,name,n,candidate")
    void deleteAll();
}
