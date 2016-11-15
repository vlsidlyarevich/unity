package com.github.vlsidlyarevich.unity.utils;

import com.github.vlsidlyarevich.unity.dto.WorkerProfileDTO;
import com.github.vlsidlyarevich.unity.model.Name;
import com.github.vlsidlyarevich.unity.model.WorkerProfile;


public final class ModelUtils {

    private ModelUtils() {
    }

    public static WorkerProfile convertToModelProfile(WorkerProfileDTO dto) {
        WorkerProfile workerProfile = new WorkerProfile();
        workerProfile.setName(dto.getName() != null ? dto.getName() : (new Name()));
        workerProfile.setAge(dto.getAge());
        workerProfile.setEmail(dto.getEmail() != null ? dto.getEmail() : "");
        workerProfile.setPhone(dto.getPhone() != null ? dto.getPhone() : "");
        workerProfile.setGender(dto.getGender());
        workerProfile.setSpeciality(dto.getSpeciality());
        workerProfile.setImageId(dto.getImageId());
        workerProfile.setDescription(dto.getDescription() != null ? dto.getDescription() : "");
        workerProfile.setBirthday(dto.getBirthday() != null ? dto.getBirthday() : "");
        workerProfile.setSkype(dto.getSkype() != null ? dto.getSkype() : "");
        workerProfile.setGithubUrl(dto.getGithubUrl() != null ? dto.getGithubUrl() : "");
        workerProfile.setLinkedInUrl(dto.getLinkedInUrl() != null ? dto.getLinkedInUrl() : "");

        return workerProfile;
    }
}
