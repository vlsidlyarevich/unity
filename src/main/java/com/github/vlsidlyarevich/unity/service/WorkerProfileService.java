package com.github.vlsidlyarevich.unity.service;

import com.github.vlsidlyarevich.unity.dto.WorkerProfileDTO;
import com.github.vlsidlyarevich.unity.model.Name;
import com.github.vlsidlyarevich.unity.model.WorkerProfile;
import com.github.vlsidlyarevich.unity.service.common.BaseService;

import java.util.List;


public interface WorkerProfileService extends BaseService<WorkerProfile, WorkerProfileDTO> {

    WorkerProfile findByName(Name name);

    List<WorkerProfile> findAllByAge(Integer age);
}
