package com.github.vlsidlyarevich.unity.service.mapper;

import com.github.vlsidlyarevich.unity.dto.CandidateDTO;
import com.github.vlsidlyarevich.unity.dto.VacancyDTO;
import com.github.vlsidlyarevich.unity.dto.WorkerProfileDTO;
import com.github.vlsidlyarevich.unity.model.*;

import java.util.ArrayList;
import java.util.HashSet;


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
        workerProfile.setSpeciality(dto.getSpeciality() != null ? dto.getSpeciality() : Speciality.UNKNOWN);
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
        vacancy.setSpeciality(dto.getSpeciality() != null ? dto.getSpeciality() : Speciality.UNKNOWN);
        vacancy.setJobType(dto.getJobType() != null ? dto.getJobType() : JobType.UNKNOWN);
        vacancy.setLocation(dto.getLocation() != null ? dto.getLocation() : "");
        vacancy.setSalary(dto.getSalary() != null ? dto.getSalary() : "");
        vacancy.setDescription(dto.getDescription() != null ? dto.getDescription() : "");

        return vacancy;
    }

    public static Candidate convertToModel(CandidateDTO dto) {
        Candidate candidate = new Candidate();
        candidate.setName(dto.getName() != null ? dto.getName() : (new Name()));
        candidate.setAge(dto.getAge());
        candidate.setGender(dto.getGender());
        candidate.setImageId(dto.getImageId());
        candidate.setBirthday(dto.getBirthday() != null ? dto.getBirthday() : "");
        candidate.setSkype(dto.getSkype() != null ? dto.getSkype() : "");
        candidate.setHrSkype(dto.getHrSkype() != null ? dto.getHrSkype() : "");
        candidate.setGithubUrl(dto.getGithubUrl() != null ? dto.getGithubUrl() : "");
        candidate.setLinkedInUrl(dto.getLinkedInUrl() != null ? dto.getLinkedInUrl() : "");

        return candidate;
    }
}
