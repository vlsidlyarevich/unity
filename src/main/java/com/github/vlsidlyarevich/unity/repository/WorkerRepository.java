package com.github.vlsidlyarevich.unity.repository;

import com.github.vlsidlyarevich.unity.models.Name;
import com.github.vlsidlyarevich.unity.models.Speciality;
import com.github.vlsidlyarevich.unity.models.Worker;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by vlad on 15.09.16.
 */

public interface WorkerRepository extends MongoRepository<Worker, String> {

    Worker findByName(Name name);

    List<Worker> findAllByAge(Integer age);

    List<Worker> findAllBySpeciality(Speciality speciality);

}
