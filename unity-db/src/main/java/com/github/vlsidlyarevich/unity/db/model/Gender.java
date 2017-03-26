package com.github.vlsidlyarevich.unity.db.model;

import lombok.ToString;

@ToString
public enum Gender {
    MALE,
    FEMALE;

    Gender() {
    }

    @Override
    public String toString() {
        return name().equals(MALE) ? "Male" : "Female";
    }
}
