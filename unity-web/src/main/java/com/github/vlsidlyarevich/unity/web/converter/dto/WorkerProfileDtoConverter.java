package com.github.vlsidlyarevich.unity.web.converter.dto;

import com.github.vlsidlyarevich.unity.web.dto.WorkerProfileDTO;
import com.github.vlsidlyarevich.unity.web.model.Name;
import com.github.vlsidlyarevich.unity.web.model.Speciality;
import com.github.vlsidlyarevich.unity.web.model.WorkerProfile;
import org.springframework.core.convert.converter.Converter;


public class WorkerProfileDtoConverter implements Converter<WorkerProfileDTO, WorkerProfile> {

    @Override
    public WorkerProfile convert(WorkerProfileDTO dto) {
        WorkerProfile workerProfile = new WorkerProfile();
        workerProfile.setName(dto.getName() != null ? dto.getName() : (new Name()));
        workerProfile.setAge(dto.getAge());
        workerProfile.setEmail(dto.getEmail() != null ? dto.getEmail() : "");
        workerProfile.setPhone(dto.getPhone() != null ? dto.getPhone() : "");
        workerProfile.setGender(dto.getGender());
        workerProfile.setSpeciality(dto.getSpeciality() != null ? dto.getSpeciality() : Speciality.UNKNOWN);
        workerProfile.setImageId(dto.getImageId());
        workerProfile.setDescription(dto.getDescription() != null ? dto.getDescription() : "");
        workerProfile.setBirthday(dto.getBirthday() != null ? dto.getBirthday() : "");
        workerProfile.setSkype(dto.getSkype() != null ? dto.getSkype() : "");
        workerProfile.setGithubUrl(dto.getGithubUrl() != null ? dto.getGithubUrl() : "");
        workerProfile.setLinkedInUrl(dto.getLinkedInUrl() != null ? dto.getLinkedInUrl() : "");

        return workerProfile;
    }
}
