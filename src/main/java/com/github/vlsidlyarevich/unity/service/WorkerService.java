package com.github.vlsidlyarevich.unity.service;

import com.github.vlsidlyarevich.unity.models.Worker;
import com.github.vlsidlyarevich.unity.models.Name;
import com.github.vlsidlyarevich.unity.models.Speciality;
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

    public Worker findByName(Name name) {
        return repository.findByName(name);
    }

    public List<Worker> findAllByAge(Integer age) {
        return repository.findAllByAge(age);
    }

    public List<Worker> findAll() {
        return repository.findAll();
    }

    public List<Worker> findAllBySpeciality(Speciality speciality) {
        return repository.findAllBySpeciality(speciality);
    }

}
