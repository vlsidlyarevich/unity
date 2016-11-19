package com.github.vlsidlyarevich.unity.dto;

import com.github.vlsidlyarevich.unity.model.JobType;
import com.github.vlsidlyarevich.unity.model.Speciality;
import lombok.Data;
import lombok.ToString;
import org.apache.commons.lang3.EnumUtils;

import java.io.Serializable;
import java.util.Date;


@Data
@ToString
public class VacancyDTO implements Serializable {

    private Speciality speciality;
    private JobType jobType;
    private Date lastUpdateDate;
    private Date datePosted;
    private String location;
    private String salary;
    private String description;

    public VacancyDTO() {
    }

    public void setSpeciality(String speciality) {
        if (EnumUtils.isValidEnum(Speciality.class, speciality)) {
            this.speciality = Speciality.valueOf(speciality);
        }
    }

    public void setSpeciality(Speciality speciality) {
        this.speciality = speciality;
    }

    public void setJobType(String jobType) {
        if (EnumUtils.isValidEnum(JobType.class, jobType)) {
            this.jobType = JobType.valueOf(jobType);
        }
    }

    public void setJobType(JobType jobType) {
        this.jobType = jobType;
    }
}
