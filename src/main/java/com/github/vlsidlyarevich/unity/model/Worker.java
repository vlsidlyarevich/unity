package com.github.vlsidlyarevich.unity.model;

import lombok.Data;
import lombok.ToString;
import org.apache.commons.lang3.EnumUtils;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;


@Data
@ToString
@Component
@NodeEntity(label = "Worker")
public class Worker extends BaseEntity {

    @Relationship(type = "HAS_NAME", direction = Relationship.OUTGOING)
    private Name name;
    private Integer age;
    private String email;
    private String phone;
    private Gender gender;
    private Speciality speciality;


    @PostConstruct
    public void init() {
        this.gender = Gender.MALE;
        this.speciality = Speciality.UNKNOWN;
    }

    public Worker() {

    }

    public Worker(Name name) {
        this.name = name;
    }

    public Worker(String firstName, String lastName) {
        this.name = new Name(firstName, lastName);
    }

    public Worker(Name name, Integer age, Speciality speciality) {
        this.name = name;
        this.age = age;
        this.speciality = speciality;
    }

    public void setSpeciality(String speciality) {
        if (EnumUtils.isValidEnum(Speciality.class, speciality)) {
            this.speciality = Speciality.valueOf(speciality);
        }
    }

    public void setSpeciality(Speciality speciality) {
        this.speciality = speciality;
    }

    public void setGender(String gender) {
        if (EnumUtils.isValidEnum(Gender.class, gender)) {
            this.gender = Gender.valueOf(gender);
        }
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}
