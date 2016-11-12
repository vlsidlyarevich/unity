package com.github.vlsidlyarevich.unity.service.impl;

import com.github.vlsidlyarevich.unity.dto.WorkerProfileDTO;
import com.github.vlsidlyarevich.unity.model.Name;
import com.github.vlsidlyarevich.unity.model.WorkerProfile;
import com.github.vlsidlyarevich.unity.repository.WorkerProfileRepository;
import com.github.vlsidlyarevich.unity.service.WorkerProfileService;
import com.github.vlsidlyarevich.unity.utils.ModelUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * Created by vlad on 28.09.16.
 */
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
        return repository.findAll();
    }

    @Override
    public String update(String id, WorkerProfileDTO dto) {
        WorkerProfile workerProfile = ModelUtils.convertToModelProfile(dto);
        workerProfile.setId(id);
        if (repository.exists(id)) {
            workerProfile.setCreatedAt(repository.findById(id).getCreatedAt());
            workerProfile.setUpdatedAt(String.valueOf(LocalDateTime.now()));
        } else {
            workerProfile.setCreatedAt(String.valueOf(LocalDateTime.now()));
        }
        repository.save(workerProfile);
        return id;
    }

    @Override
    public String delete(String id) {
        repository.delete(id);
        return id;
    }

    @Override
    public String deleteQuery(Map<String, String> ids) {
        Integer deleteCounter = 0;

        if (ids.keySet().size() == 1 && ids.containsValue("all")) {
            deleteCounter = repository.findAll().size();
            repository.deleteAll();
            return String.valueOf(deleteCounter);
        }

        for (Map.Entry<String, String> id : ids.entrySet()) {
            if (repository.exists(id.getValue())) {
                repository.delete(id.getValue());
                deleteCounter++;
            }
        }
        return String.valueOf(deleteCounter);
    }
}
