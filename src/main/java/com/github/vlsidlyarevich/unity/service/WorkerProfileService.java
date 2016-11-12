package com.github.vlsidlyarevich.unity.service;

import com.github.vlsidlyarevich.unity.dto.WorkerProfileDTO;
import com.github.vlsidlyarevich.unity.model.Name;
import com.github.vlsidlyarevich.unity.model.WorkerProfile;

import java.util.List;
import java.util.Map;

/**
 * Created by vladislav on 10/8/16.
 */
public interface WorkerProfileService extends BaseService<WorkerProfile, WorkerProfileDTO> {

    WorkerProfile findByName(Name name);

    List<WorkerProfile> findAllByAge(Integer age);

    String deleteQuery(Map<String, String> ids);
}
