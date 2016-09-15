package com.github.vlsidlyarevich.unity.service;

import com.github.vlsidlyarevich.unity.domain.Worker;
import com.github.vlsidlyarevich.unity.repository.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by vlad on 15.09.16.
 */
@Service
public class WorkerService {

    @Autowired
    private WorkerRepository repository;

    public Worker findByFirstName(String firstName) {
        return repository.findByFirstName(firstName);
    }

    public Worker findByLastName(String lastName) {
        return repository.findByFirstName(lastName);
    }

    public List<Worker> findAllByAge(Integer age) {
        return repository.findAllByAge(age);
    }

    public List<Worker> findAllByFirstName(String firstName) {
        return repository.findAllByFirstName(firstName);
    }

    public List<Worker> findAllByLastName(String lastName) {
        return repository.findAllByLastName(lastName);
    }

}
