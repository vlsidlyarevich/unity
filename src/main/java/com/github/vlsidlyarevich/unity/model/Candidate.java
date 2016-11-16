package com.github.vlsidlyarevich.unity.model;

import lombok.Data;
import lombok.ToString;
import org.neo4j.ogm.annotation.NodeEntity;


@Data
@ToString
@NodeEntity(label = "Candidate")
public class Candidate extends BaseEntityNeo4j {

    private String HrSkype;
    private String skype;
    private String githubUrl;
    private String linkedInUrl;

    public Candidate(){
    }

    public Candidate(String hrSkype, String skype, String githubUrl, String linkedInUrl) {
        HrSkype = hrSkype;
        this.skype = skype;
        this.githubUrl = githubUrl;
        this.linkedInUrl = linkedInUrl;
    }
}
