package com.github.vlsidlyarevich.unity.common.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AnalyzedResource {
    GITHUB("github"),
    FACEBOOK("facebook"),
    TWITTER("twitter"),
    UNKNOWN("unknown");

    private String name;
}
