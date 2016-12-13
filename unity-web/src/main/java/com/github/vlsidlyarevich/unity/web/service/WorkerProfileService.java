package com.github.vlsidlyarevich.unity.web.service;

import com.github.vlsidlyarevich.unity.web.dto.WorkerProfileDTO;
import com.github.vlsidlyarevich.unity.web.model.Name;
import com.github.vlsidlyarevich.unity.web.model.WorkerProfile;
import com.github.vlsidlyarevich.unity.web.service.common.BaseService;

import java.util.List;


public interface WorkerProfileService extends BaseService<WorkerProfile, WorkerProfileDTO> {

    WorkerProfile findByName(Name name);

    List<WorkerProfile> findAllByAge(Integer age);

    String deleteImage(String id);
}
