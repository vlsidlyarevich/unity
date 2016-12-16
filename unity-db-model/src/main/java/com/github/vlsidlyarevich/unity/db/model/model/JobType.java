package com.github.vlsidlyarevich.unity.db.model.model;

import lombok.ToString;


@ToString
public enum JobType {
    CONTRACT,
    UNKNOWN,
    PERMANENT;

    JobType() {
    }
}
