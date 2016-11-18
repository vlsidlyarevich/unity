package com.github.vlsidlyarevich.unity.model;

import lombok.Data;
import lombok.ToString;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;


@Data
@ToString
@NodeEntity(label = "Candidate")
public class Candidate extends BaseEntity {

    @Relationship(type = "HAS")
    private Name name;
    private String HrSkype;
    private String skype;
    private String githubUrl;
    private String linkedInUrl;

    public Candidate() {
    }

    public Candidate(Name name) {
        this.name = name;
    }

    public Candidate(Name name, String hrSkype, String skype, String githubUrl, String linkedInUrl) {
        this.name = name;
        HrSkype = hrSkype;
        this.skype = skype;
        this.githubUrl = githubUrl;
        this.linkedInUrl = linkedInUrl;
    }
}
