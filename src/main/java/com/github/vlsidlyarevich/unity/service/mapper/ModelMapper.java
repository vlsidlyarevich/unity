package com.github.vlsidlyarevich.unity.service.mapper;

import com.github.vlsidlyarevich.unity.dto.VacancyDTO;
import com.github.vlsidlyarevich.unity.dto.WorkerProfileDTO;
import com.github.vlsidlyarevich.unity.model.Name;
import com.github.vlsidlyarevich.unity.model.Vacancy;
import com.github.vlsidlyarevich.unity.model.WorkerProfile;


public final class ModelMapper {

    private ModelMapper() {
    }

    public static WorkerProfile convertToModel(WorkerProfileDTO dto) {
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

    public static Vacancy convertToModel(VacancyDTO dto) {
        Vacancy vacancy = new Vacancy();
        vacancy.setSpeciality(dto.getSpeciality());
        vacancy.setJobType(dto.getJobType());
        vacancy.setLocation(dto.getLocation() != null ? dto.getLocation() : "");
        vacancy.setSalary(dto.getSalary() != null ? dto.getSalary() : "");
        vacancy.setDescription(dto.getDescription() != null ? dto.getDescription() : "");

        return vacancy;
    }
}
