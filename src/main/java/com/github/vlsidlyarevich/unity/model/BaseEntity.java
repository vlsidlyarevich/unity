package com.github.vlsidlyarevich.unity.model;

import lombok.Data;
import org.neo4j.ogm.annotation.GraphId;

import java.io.Serializable;


@Data
public class BaseEntity implements Serializable {

    @GraphId
    private Long id;
    private String createdAt;
    private String updatedAt;

    public BaseEntity() {
    }
}
