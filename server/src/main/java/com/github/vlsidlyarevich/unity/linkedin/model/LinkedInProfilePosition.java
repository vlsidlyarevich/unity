package com.github.vlsidlyarevich.unity.linkedin.model;

import com.github.vlsidlyarevich.unity.common.model.LinkedInResult;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class LinkedInProfilePosition extends LinkedInResult {

    private static final long serialVersionUID = 5161392053798642677L;

    private LinkedInCompany company;
    private String id;
    private boolean isCurrent;
    private LinkedInDate startDate;
    private LinkedInDate endDate;
    private String summary;
    private String title;
}
