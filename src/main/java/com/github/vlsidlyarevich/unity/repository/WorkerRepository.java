package com.github.vlsidlyarevich.unity.repository;

import com.github.vlsidlyarevich.unity.domain.Worker;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by vlad on 15.09.16.
 */
public interface WorkerRepository extends MongoRepository<Worker, String> {

    public Worker findByFirstName(String firstName);

    public Worker findByLastName(String lastName);

    public List<Worker> findAllByAge(Integer age);

}
