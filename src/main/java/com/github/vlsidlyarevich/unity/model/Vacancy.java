package com.github.vlsidlyarevich.unity.model;

import lombok.Data;
import lombok.ToString;
import org.apache.commons.lang3.EnumUtils;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Data
@ToString
@Component
@NodeEntity(label = "Vacansy")
public class Vacancy extends BaseEntityNeo4j {

    private Speciality speciality;
    private JobType jobType;
    private Date lastUpdateDate;
    private Date datePosted;
    private String location;
    private String salary;
    private String description;

    @Relationship(type = "CANDIDATE", direction = Relationship.UNDIRECTED)
    private Set<Candidate> candidates;

    @PostConstruct
    public void init() {
        this.jobType = JobType.CONTRACT;
        this.speciality = Speciality.UNKNOWN;
    }

    public Vacancy() {
    }

    public Vacancy(Speciality speciality) {
        this.speciality = speciality;
    }

    public Vacancy(Speciality speciality, JobType jobType) {
        this.speciality = speciality;
        this.jobType = jobType;
    }

    public void addCandidate(Candidate candidate) {
        if (candidates == null) {
            candidates = new HashSet<>();
        }

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
