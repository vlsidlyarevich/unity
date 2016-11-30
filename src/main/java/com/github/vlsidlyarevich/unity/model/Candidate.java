package com.github.vlsidlyarevich.unity.model;

import lombok.Data;
import lombok.ToString;


@Data
@ToString
public class Candidate extends Person {

    private String hrSkype;
    private String skype;
    private String githubUrl;
    private String linkedInUrl;
    private String imageId;

    public Candidate() {

    }

    public Candidate(Name name) {
        super(name);
    }

    public Candidate(String firstName, String lastName) {
        super(new Name(firstName, lastName));
    }

    public Candidate(Name name, Integer age, Gender gender, String birthday, String hrSkype,
                     String skype, String githubUrl, String linkedInUrl, String imageId) {
        super(name, age, gender, birthday);
        this.hrSkype = hrSkype;
        this.skype = skype;
        this.githubUrl = githubUrl;
        this.linkedInUrl = linkedInUrl;
        this.imageId = imageId;
    }
}

