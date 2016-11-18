package com.github.vlsidlyarevich.unity.service.impl;

import com.github.vlsidlyarevich.unity.dto.WorkerProfileDTO;
import com.github.vlsidlyarevich.unity.model.Name;
import com.github.vlsidlyarevich.unity.model.WorkerProfile;
import com.github.vlsidlyarevich.unity.repository.WorkerProfileRepository;
import com.github.vlsidlyarevich.unity.service.WorkerProfileService;
import com.github.vlsidlyarevich.unity.utils.ModelUtils;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;


@Service
public class WorkerProfileServiceImpl implements WorkerProfileService {

    @Autowired
    private WorkerProfileRepository repository;

    @Override
    public WorkerProfile create(WorkerProfileDTO dto) {
        WorkerProfile workerProfile = ModelUtils.convertToModelProfile(dto);
        workerProfile.setCreatedAt(String.valueOf(LocalDateTime.now()));
        repository.save(workerProfile);
        return workerProfile;
    }

    @Override
    public WorkerProfile find(Long id) {
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
    public Long update(Long id, WorkerProfileDTO dto) {
        WorkerProfile workerProfile = ModelUtils.convertToModelProfile(dto);
        workerProfile.setId(id);

        WorkerProfile saved = repository.findById(id);

        if (saved != null) {
            workerProfile.getName().setId(saved.getName().getId());
            workerProfile.setCreatedAt(saved.getCreatedAt());
            workerProfile.setUpdatedAt(String.valueOf(LocalDateTime.now()));
        } else {
            workerProfile.setCreatedAt(String.valueOf(LocalDateTime.now()));
        }
        repository.save(workerProfile);
        return id;
    }

    @Override
    public Long delete(Long id) {
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
            if (repository.exists(Long.valueOf(id.getValue()))) {
                repository.delete(Long.valueOf(id.getValue()));
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
