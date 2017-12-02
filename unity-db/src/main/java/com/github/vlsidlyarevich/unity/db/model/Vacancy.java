package com.github.vlsidlyarevich.unity.db.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.EnumUtils;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@Entity
public class Vacancy extends BaseEntity {

    @Column
    private Speciality speciality;
    @Column(name = "job_type")
    private JobType jobType;
    @Column
    private String location;
    @Column
    private String salary;
    @Column
    private String description;

    @OneToMany(mappedBy = "vacancy", fetch = FetchType.EAGER, cascade = {CascadeType.REMOVE, CascadeType.PERSIST, CascadeType.MERGE},
            orphanRemoval = true)
    @JsonManagedReference
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

    public void setSpeciality(String speciality) {
        if (EnumUtils.isValidEnum(Speciality.class, speciality)) {
            this.speciality = Speciality.valueOf(speciality);
        }
    }

    public void setSpeciality(Speciality speciality) {
        this.speciality = speciality;
    }

    public List<Candidate> getCandidates() {
        if (this.candidates == null) {
            this.candidates = new ArrayList<>();
            return candidates;
        }
        return candidates;
    }
}
