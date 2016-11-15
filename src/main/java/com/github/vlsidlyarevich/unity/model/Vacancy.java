package com.github.vlsidlyarevich.unity.model;

import lombok.Data;
import lombok.ToString;
import org.apache.commons.lang3.EnumUtils;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by vlad on 14/11/16.
 */
@Data
@ToString
@Component
public class Vacancy extends BaseEntity {

    private Speciality speciality;
    private JobType jobType;
    private String lastUpdateDate;
    private String datePosted;
    private String location;
    private String salary;
    private String description;

    @PostConstruct
    public void init() {
        this.jobType = JobType.CONTRACT;
        this.speciality = Speciality.UNKNOWN;
    }

    public Vacancy(){
    }

    public Vacancy(Speciality speciality) {
        this.speciality = speciality;
    }

    public Vacancy(Speciality speciality, JobType jobType) {
        this.speciality = speciality;
        this.jobType = jobType;
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
