package com.github.vlsidlyarevich.unity.web.model;

import lombok.ToString;


@ToString
public enum JobType {
    CONTRACT,
    UNKNOWN,
    PERMANENT;

    JobType() {
    }
}
