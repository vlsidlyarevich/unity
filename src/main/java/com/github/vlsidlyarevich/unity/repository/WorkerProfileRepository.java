package com.github.vlsidlyarevich.unity.repository;


import com.github.vlsidlyarevich.unity.models.Name;
import com.github.vlsidlyarevich.unity.models.Speciality;
import com.github.vlsidlyarevich.unity.models.WorkerProfile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkerProfileRepository extends MongoRepository<WorkerProfile, String> {

    WorkerProfile findById(Long id);

    WorkerProfile findByName(Name name);

    List<WorkerProfile> findAllByAge(Integer age);

    List<WorkerProfile> findAllBySpeciality(Speciality speciality);
}
