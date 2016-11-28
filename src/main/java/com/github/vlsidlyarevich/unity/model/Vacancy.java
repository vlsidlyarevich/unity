package com.github.vlsidlyarevich.unity.model;

import lombok.Data;
import lombok.ToString;
import org.apache.commons.lang3.EnumUtils;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.HashSet;
import java.util.Set;


@Data
@ToString
@NodeEntity(label = "Vacancy")
public class Vacancy extends BaseEntity {

    private Speciality speciality;
    private JobType jobType;
    private String location;
    private String salary;
    private String description;

    @Relationship(type = "HAS_CANDIDATE", direction = Relationship.UNDIRECTED)
    private Set<Candidate> candidates;

    public Vacancy() {
        this.jobType = JobType.UNKNOWN;
        this.speciality = Speciality.UNKNOWN;
        this.candidates = new HashSet<>();
    }

    public Vacancy(Speciality speciality) {
        this.speciality = speciality;
    }

    public Vacancy(Speciality speciality, JobType jobType) {
        this.speciality = speciality;
        this.jobType = jobType;
    }

    public void addCandidate(Candidate candidate) {
        candidates.add(candidate);
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
