package com.github.vlsidlyarevich.unity.repository;

import com.github.vlsidlyarevich.unity.domain.Worker;
import com.github.vlsidlyarevich.unity.models.Name;
import com.github.vlsidlyarevich.unity.models.Speciality;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by vlad on 15.09.16.
 */
@Repository
public interface WorkerRepository extends MongoRepository<Worker, String> {

    Worker findByName(Name name);

    List<Worker> findAllByAge(Integer age);

    List<Worker> findAllBySpeciality(Speciality speciality);

}
