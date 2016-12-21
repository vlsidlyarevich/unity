package com.github.vlsidlyarevich.unity.db.model;

import lombok.Data;
import lombok.ToString;
import org.apache.commons.lang3.EnumUtils;


@Data
@ToString
public class Worker extends Person {

    private String email;
    private String phone;
    private Speciality speciality;

    public Worker() {
        this.setGender(Gender.MALE);
        this.speciality = Speciality.UNKNOWN;
    }

    public Worker(Name name) {
        super(name);
    }

    public Worker(String firstName, String lastName) {
        super(new Name(firstName, lastName));
    }

    public Worker(Name name, Integer age, Gender gender, String birthday,
                  String email, String phone, Speciality speciality) {
        super(name, age, gender, birthday);
        this.email = email;
        this.phone = phone;
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
