package com.github.vlsidlyarevich.unity.models;

import lombok.Data;

@Data
public class WorkerProfile extends Worker {

    private String imageUrl;
    private String description;

    public WorkerProfile() {

    }

    public WorkerProfile(String firstName, String lastName, String imageUrl, String description) {
        super(firstName, lastName);
        this.imageUrl = imageUrl;
        this.description = description;
    }

}
