package com.github.vlsidlyarevich.unity.model;

import lombok.Data;
import org.neo4j.ogm.annotation.GraphId;

import java.io.Serializable;
import java.util.Date;

//TODO:Remove after integration of neo4j
@Data
public class BaseEntityNeo4j implements Serializable {

    @GraphId
    private Long id;
    private Date createdAt;
    private Date updatedAt;

    public BaseEntityNeo4j() {
    }
}
