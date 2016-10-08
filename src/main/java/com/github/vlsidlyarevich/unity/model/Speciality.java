package com.github.vlsidlyarevich.unity.model;

import lombok.ToString;

/**
 * Created by vlad on 18.09.16.
 */
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
