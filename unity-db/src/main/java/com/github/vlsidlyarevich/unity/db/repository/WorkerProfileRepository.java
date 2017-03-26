package com.github.vlsidlyarevich.unity.db.repository;

import com.github.vlsidlyarevich.unity.db.model.Name;
import com.github.vlsidlyarevich.unity.db.model.Speciality;
import com.github.vlsidlyarevich.unity.db.model.WorkerProfile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkerProfileRepository extends MongoRepository<WorkerProfile, String> {

    WorkerProfile findById(String id);

    WorkerProfile findByName(Name name);

    List<WorkerProfile> findAllByAge(Integer age);

    List<WorkerProfile> findAllBySpeciality(Speciality speciality);

}
