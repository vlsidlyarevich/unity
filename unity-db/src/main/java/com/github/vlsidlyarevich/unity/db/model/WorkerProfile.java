package com.github.vlsidlyarevich.unity.db.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;


@Data
@Entity
public class WorkerProfile extends BaseEntity {

    @Embedded
    private Name name;
    @Column
    private Integer age;
    @Column
    private Gender gender;
    @Column
    private String birthday;
    @Column
    private String email;
    @Column
    private String phone;
    @Column(name = "speciality")
    private Speciality speciality;
    @Column
    private String imageId;
    @Column
    private String description;
    @Column(name = "skype", nullable = false)
    private String skype;
    @Column(name = "github_url")
    private String githubUrl;
    @Column(name = "linkedin_url")
    private String linkedInUrl;
}
