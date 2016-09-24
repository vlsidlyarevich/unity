package com.github.vlsidlyarevich.unity.models;

import lombok.Data;
import lombok.ToString;
import org.apache.commons.lang3.EnumUtils;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by vlad on 15.09.16.
 */
@Data
@ToString
@Component
public class Worker extends BaseEntity {

    private Name name;
    private Integer age;
    private String email;
    private String phone;
    private Speciality speciality;


    @PostConstruct
    public void init() {
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
}
