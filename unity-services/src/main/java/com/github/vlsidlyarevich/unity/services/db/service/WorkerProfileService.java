package com.github.vlsidlyarevich.unity.services.db.service;

import com.github.vlsidlyarevich.unity.web.dto.WorkerProfileDTO;
import com.github.vlsidlyarevich.unity.db.model.model.Name;
import com.github.vlsidlyarevich.unity.db.model.model.WorkerProfile;
import com.github.vlsidlyarevich.unity.services.db.service.common.BaseService;

import java.util.List;


public interface WorkerProfileService extends BaseService<WorkerProfile, WorkerProfileDTO> {

    WorkerProfile findByName(Name name);

    List<WorkerProfile> findAllByAge(Integer age);

    String deleteImage(String id);
}
