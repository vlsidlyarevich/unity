package com.github.vlsidlyarevich.unity.linkedin.model;

import com.github.vlsidlyarevich.unity.common.model.LinkedInResult;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class LinkedInDate extends LinkedInResult {

    private static final long serialVersionUID = 8563017367200458284L;

    private int year;
    private int month;
    private int day;
}
