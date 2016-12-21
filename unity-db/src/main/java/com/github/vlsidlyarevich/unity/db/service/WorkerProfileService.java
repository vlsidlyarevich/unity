package com.github.vlsidlyarevich.unity.db.service;

import com.github.vlsidlyarevich.unity.db.model.Name;
import com.github.vlsidlyarevich.unity.db.model.WorkerProfile;
import com.github.vlsidlyarevich.unity.db.service.common.BaseService;

import java.util.List;


public interface WorkerProfileService extends BaseService<WorkerProfile> {

    WorkerProfile findByName(Name name);

    List<WorkerProfile> findAllByAge(Integer age);

    String deleteImage(String id);
}
