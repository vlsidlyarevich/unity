package com.github.vlsidlyarevich.unity.dto;

import com.github.vlsidlyarevich.unity.model.Name;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;


@Data
@ToString
public class CandidateDTO implements Serializable {

    private Name name;
    private String hrSkype;
    private String skype;
    private String githubUrl;
    private String linkedInUrl;
    private String imageId;

    public CandidateDTO() {
    }

    public CandidateDTO(Name name, String hrSkype, String skype, String githubUrl, String linkedInUrl, String imageId) {
        this.name = name;
        this.hrSkype = hrSkype;
        this.skype = skype;
        this.githubUrl = githubUrl;
        this.linkedInUrl = linkedInUrl;
        this.imageId = imageId;
    }
}
