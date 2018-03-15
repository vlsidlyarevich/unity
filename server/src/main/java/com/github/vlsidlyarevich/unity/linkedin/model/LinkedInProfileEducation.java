package com.github.vlsidlyarevich.unity.linkedin.model;

import com.github.vlsidlyarevich.unity.common.model.LinkedInResult;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class LinkedInProfileEducation extends LinkedInResult {

    private static final long serialVersionUID = 2437059263932739313L;

    private String activities;
    private String degree;
    private String fieldOfStudy;
    private String id;
    private String notes;
    private String schoolName;
    private LinkedInDate startDate;
    private LinkedInDate endDate;
}
