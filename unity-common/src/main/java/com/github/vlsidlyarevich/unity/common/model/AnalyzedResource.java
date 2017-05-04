package com.github.vlsidlyarevich.unity.common.model;

public enum AnalyzedResource {
    GITHUB("github"),
    FACEBOOK("facebook"),
    TWITTER("twitter"),
    UNKNOWN("unknown");

    private String name;

    AnalyzedResource(String string) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
