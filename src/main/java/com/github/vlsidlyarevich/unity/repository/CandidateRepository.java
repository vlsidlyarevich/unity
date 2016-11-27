package com.github.vlsidlyarevich.unity.repository;

import com.github.vlsidlyarevich.unity.model.Candidate;
import com.github.vlsidlyarevich.unity.model.Vacancy;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface CandidateRepository extends GraphRepository<Vacancy> {

    @Query("MATCH (n) WHERE id(n)={0} RETURN n")
    Vacancy findById(Long id);

    @Query("MATCH (vacancy:Vacancy)" +
            "WHERE id(vacancy)={vacancyId}" +
            "CREATE (vacancy)-[:HAS_CANDIDATE]->(candidate:Candidate {hrSkype:{hrSkype}, skype:{skype}, githubUrl:{githubUrl}, " +
            "linkedInUrl:{linkedInUrl}, imageId:{imageId}})" +
            "RETURN candidate")
    Candidate create(@Param("vacancyId") String vacancyId, @Param("hrSkype") String hrSkype,
                     @Param("firstName") String firstName, @Param("secondName") String secondName,
                     @Param("skype") String skype, @Param("githubUrl") String githubUrl,
                     @Param("linkedInUrl") String linkedInUrl, @Param("imageId") String imageId);

    @Query("MATCH (vacancy:Vacancy)-[r:HAS_CANDIDATE]->(candidate:Candidate)" +
            "WHERE id(vacancy)={vacancyId}" +
            "DELETE candidate,r")
    void deleteAllInVacancy(@Param("vacancyId") String vacancyId);

    @Query("MATCH (candidate:Candidate)-[r:VACANCY]->(vacancy:Vacancy)" +
            "DELETE candidate,r")
    void deleteAll();
}
