package com.github.vlsidlyarevich.unity.model;

import lombok.Data;
import lombok.ToString;


@Data
@ToString
public class Candidate extends Worker {

    private String HrSkype;
    private String skype;
    private String githubUrl;
    private String linkedInUrl;

    public Candidate(){
    }

    public Candidate(Name name, String hrSkype, String skype, String githubUrl, String linkedInUrl) {
        super(name);
        HrSkype = hrSkype;
        this.skype = skype;
        this.githubUrl = githubUrl;
        this.linkedInUrl = linkedInUrl;
    }
}
