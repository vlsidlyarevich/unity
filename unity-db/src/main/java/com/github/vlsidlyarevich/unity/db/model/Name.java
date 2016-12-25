package com.github.vlsidlyarevich.unity.db.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


@Data
public class Name {

    private String firstName;
    private String lastName;

    public Name() {
    }

    public Name(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
