package com.github.vlsidlyarevich.unity.repository;

import com.github.vlsidlyarevich.unity.model.Speciality;
import com.github.vlsidlyarevich.unity.model.WorkerProfile;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface WorkerProfileRepository extends GraphRepository<WorkerProfile> {

    @Query("MATCH (n) WHERE id(n) = {0} RETURN n")
    WorkerProfile findById(Long id);

    @Query("MATCH (worker:Worker)-[:HAS]->(name:Name) " +
            "WHERE name.firstName = {firstName} " +
            "AND name.lastName = {lastName}" +
            "RETURN worker")
    WorkerProfile findByName(@Param("firstName") String firstName, @Param("lastName") String lastName);

    List<WorkerProfile> findAllByAge(Integer age);

    List<WorkerProfile> findAllBySpeciality(Speciality speciality);

    @Query("MATCH (worker:Worker)-[r:HAS]->(name:Name) WHERE id(worker) = {0} DELETE worker,r,name")
    void delete(Long id);

    @Query("MATCH (worker:Worker)-[r:HAS]->(name:Name) DELETE worker,r,name")
    void deleteAll();
}
