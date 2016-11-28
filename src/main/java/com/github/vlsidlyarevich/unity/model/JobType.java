package com.github.vlsidlyarevich.unity.model;

import lombok.ToString;


@ToString
public enum JobType {
    CONTRACT,
    UNKNOWN,
    PERMANENT;

    JobType() {
    }
}
