package com.github.vlsidlyarevich.unity.db.service.impl;

import com.github.vlsidlyarevich.unity.db.model.Name;
import com.github.vlsidlyarevich.unity.db.model.WorkerProfile;
import com.github.vlsidlyarevich.unity.db.repository.WorkerProfileRepository;
import com.github.vlsidlyarevich.unity.db.service.StorageService;
import com.github.vlsidlyarevich.unity.db.service.WorkerProfileService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;


@Service
public class WorkerProfileServiceImpl implements WorkerProfileService {

    @Autowired
    private WorkerProfileRepository repository;

    @Autowired
    private StorageService storageService;

    @Override
    public WorkerProfile create(WorkerProfile workerProfile) {
        repository.save(workerProfile);
        return workerProfile;
    }

    @Override
    public WorkerProfile find(String id) {
        return repository.findById(id);
    }

    @Override
    public WorkerProfile findByName(Name name) {
        return repository.findByName(name);
    }

    @Override
    public List<WorkerProfile> findAllByAge(Integer age) {
        return repository.findAllByAge(age);
    }

    @Override
    public List<WorkerProfile> findAll() {
        return Lists.newArrayList(repository.findAll().iterator());
    }

    @Override
    public WorkerProfile update(String id, WorkerProfile workerProfile) {
        workerProfile.setId(id);

        WorkerProfile saved = repository.findById(id);

        if (saved != null) {
            if (!Objects.equals(saved.getImageId(), workerProfile.getImageId())) {
                storageService.delete(saved.getImageId());
            }
        }
        repository.save(workerProfile);
        return workerProfile;
    }

    @Override
    public String deleteImage(String id) {
        String imageId = repository.findById(id).getImageId();
        if (storageService.exists(imageId)) {
            storageService.delete(imageId);
        }
        return id;
    }


    @Override
    public String delete(String id) {
        deleteImage(id);
        repository.delete(id);
        return id;
    }

    @Override
    public Integer deleteQuery(Map<String, String> ids) {
        Integer deleteCounter = 0;

        if (ids.keySet().size() == 1 && ids.containsValue("all")) {
            deleteCounter = Math.toIntExact(repository.count());
            repository.deleteAll();

            return deleteCounter;
        }

        for (Map.Entry<String, String> id : ids.entrySet()) {
            if (repository.exists(id.getValue())) {
                deleteImage(id.getValue());
                repository.delete(id.getValue());
                deleteCounter++;
            }
        }
        return deleteCounter;
    }

    @Override
    public Integer deleteAll() {
        Integer deleteCounter = Math.toIntExact(repository.count());
        repository.deleteAll();
        return deleteCounter;
    }
}
