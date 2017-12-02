package com.github.vlsidlyarevich.unity.db.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Getter
@Setter
@Entity
public class Candidate extends BaseEntity {

    @Embedded
    private Name name;
    @Column
    private Integer age;
    @Column
    private Gender gender;
    @Column
    private String birthday;
    @Column(name = "hr_skype")
    private String hrSkype;
    @Column(name = "skype")
    private String skype;
    @Column(name = "github_url")
    private String githubUrl;
    @Column(name = "linkedin_url")
    private String linkedInUrl;
    @Column(name = "image_id")
    private String imageId;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "vacancy_id")
    private Vacancy vacancy;
}

