package com.github.vlsidlyarevich.unity.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.neo4j.ogm.annotation.NodeEntity;


@Data
@ToString
@EqualsAndHashCode
@NodeEntity(label = "Name")
public class Name extends BaseEntity {

    private String firstName;
    private String lastName;

    public Name() {
    }

    public Name(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
