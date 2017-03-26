package com.github.vlsidlyarevich.unity.db.model;

import lombok.Data;
import lombok.ToString;
import org.apache.commons.lang3.EnumUtils;

import java.util.ArrayList;
import java.util.List;

@Data
public class Vacancy extends BaseEntity {

    private Speciality speciality;
    private JobType jobType;
    private String location;
    private String salary;
    private String description;

    private List<Candidate> candidates;

    public Vacancy() {
        this.jobType = JobType.UNKNOWN;
        this.speciality = Speciality.UNKNOWN;
    }

    public Vacancy(Speciality speciality) {
        this.speciality = speciality;
    }

    public Vacancy(Speciality speciality, JobType jobType) {
        this.speciality = speciality;
        this.jobType = jobType;
    }

    public List<Candidate> getCandidates() {
        if (candidates == null) {
            candidates = new ArrayList<>();
        }

        return candidates;
    }

    public void setJobType(String jobType) {
        if (EnumUtils.isValidEnum(JobType.class, jobType)) {
            this.speciality = Speciality.valueOf(jobType);
        }
    }

    public void setJobType(JobType jobType) {
        this.jobType = jobType;
    }

    public void setSpeciality(String speciality) {
        if (EnumUtils.isValidEnum(Speciality.class, speciality)) {
            this.speciality = Speciality.valueOf(speciality);
        }
    }

    public void setSpeciality(Speciality speciality) {
        this.speciality = speciality;
    }
}
