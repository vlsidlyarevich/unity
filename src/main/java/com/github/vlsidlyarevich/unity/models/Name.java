package com.github.vlsidlyarevich.unity.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Created by vlad on 18.09.16.
 */
@Data
@ToString
@EqualsAndHashCode
public class Name {

    private String firstName;
    private String middleName;
    private String lastName;

    public Name() {
    }

    public Name(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Name(String firstName, String lastName, String middleName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
    }
}
