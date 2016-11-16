package com.github.vlsidlyarevich.unity.repository;

import com.github.vlsidlyarevich.unity.model.Name;
import com.github.vlsidlyarevich.unity.model.Speciality;
import com.github.vlsidlyarevich.unity.model.WorkerProfile;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface WorkerProfileRepository extends GraphRepository<WorkerProfile> {

    WorkerProfile findById(Long id);

    WorkerProfile findByName(Name name);

    List<WorkerProfile> findAllByAge(Integer age);

    List<WorkerProfile> findAllBySpeciality(Speciality speciality);
}
