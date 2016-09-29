package com.github.vlsidlyarevich.unity.service;

import com.github.vlsidlyarevich.unity.models.Name;
import com.github.vlsidlyarevich.unity.models.Speciality;
import com.github.vlsidlyarevich.unity.models.Worker;
import com.github.vlsidlyarevich.unity.models.WorkerProfile;
import com.github.vlsidlyarevich.unity.repository.WorkerProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by vlad on 28.09.16.
 */
@Service
public class WorkerProfileService {

    @Autowired
    private WorkerProfileRepository repository;

    public WorkerProfile findById(Long id) {
        return repository.findById(id);
    }

    public WorkerProfile findByName(Name name) {
        return repository.findByName(name);
    }

    public List<WorkerProfile> findAllByAge(Integer age) {
        return repository.findAllByAge(age);
    }

    public List<WorkerProfile> findAll() {
        return repository.findAll();
    }

    public List<WorkerProfile> findAllBySpeciality(Speciality speciality) {
        return repository.findAllBySpeciality(speciality);
    }

    public void updateWorkerProfileById(WorkerProfile workerProfile) {
        this.repository.updateWorkerProfileById(Long.valueOf(workerProfile.getId()), workerProfile);
    }

    public void deleteWorkerProfileById(Long id) {
        this.repository.delete(id.toString());
    }
}
