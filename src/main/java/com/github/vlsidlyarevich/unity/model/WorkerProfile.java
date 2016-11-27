package com.github.vlsidlyarevich.unity.model;

import lombok.Data;
import lombok.ToString;


@Data
@ToString
public class WorkerProfile extends Worker {

    private String imageId;
    private String description;
    private String skype;
    private String githubUrl;
    private String linkedInUrl;

    public WorkerProfile() {
    }

    public WorkerProfile(String firstName, String lastName, String imageId, String description, String skype,
                         String githubUrl, String linkedInUrl) {
        super(firstName, lastName);
        this.imageId = imageId;
        this.description = description;
        this.skype = skype;
        this.githubUrl = githubUrl;
        this.linkedInUrl = linkedInUrl;
    }
}
