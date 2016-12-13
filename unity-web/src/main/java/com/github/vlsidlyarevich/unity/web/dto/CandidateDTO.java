package com.github.vlsidlyarevich.unity.web.dto;

import com.github.vlsidlyarevich.unity.web.model.Gender;
import com.github.vlsidlyarevich.unity.web.model.Name;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;


@Data
@ToString
public class CandidateDTO implements Serializable {

    private Name name;
    private Integer age;
    private Gender gender;
    private String birthday;
    private String hrSkype;
    private String skype;
    private String githubUrl;
    private String linkedInUrl;
    private String imageId;

    public CandidateDTO() {
    }

    public CandidateDTO(Name name, Integer age, Gender gender, String birthday,
                        String hrSkype, String skype, String githubUrl, String linkedInUrl, String imageId) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.birthday = birthday;
        this.hrSkype = hrSkype;
        this.skype = skype;
        this.githubUrl = githubUrl;
        this.linkedInUrl = linkedInUrl;
        this.imageId = imageId;
    }
}
