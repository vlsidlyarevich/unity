package com.github.vlsidlyarevich.unity.web.dto;

import com.github.vlsidlyarevich.unity.db.model.Gender;
import com.github.vlsidlyarevich.unity.db.model.Name;
import com.github.vlsidlyarevich.unity.db.model.Speciality;
import lombok.Data;
import lombok.ToString;
import org.apache.commons.lang3.EnumUtils;

import java.io.Serializable;


@Data
@ToString
public class WorkerProfileDTO implements Serializable {

    private Name name;
    private Integer age;
    private String email;
    private String phone;
    private Gender gender;
    private Speciality speciality;
    private String imageId;
    private String description;
    private String birthday;
    private String skype;
    private String githubUrl;
    private String linkedInUrl;

    public WorkerProfileDTO() {
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
