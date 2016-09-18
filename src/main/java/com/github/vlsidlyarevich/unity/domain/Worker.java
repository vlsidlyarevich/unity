package com.github.vlsidlyarevich.unity.domain;

import com.github.vlsidlyarevich.unity.models.Name;
import com.github.vlsidlyarevich.unity.models.Speciality;
import lombok.Data;
import lombok.ToString;
import org.apache.commons.lang3.EnumUtils;
import org.springframework.data.annotation.Id;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by vlad on 15.09.16.
 */
@Data
@ToString
public class Worker {

    @Id
    private String id;

    private Name name;

    private Integer age;

    private Speciality speciality;

    @PostConstruct
    public void init() {
        this.speciality = Speciality.UNKNOWN;
    }

    public Worker() {

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
