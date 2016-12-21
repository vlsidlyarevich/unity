package com.github.vlsidlyarevich.unity.db.model;

import lombok.ToString;


@ToString
public enum Speciality {
    UNKNOWN,
    SOFTWARE_ENGINEER,
    BUSINESS_ANALYST,
    PROJECT_MANAGER,
    TECHNICAL_LEADER,
    QUALITY_ASSURANCE,
    RESOURCE_MANAGER,
    SYSTEM_ADMINISTRATOR;

    Speciality() {
    }
}
