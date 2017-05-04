package com.github.vlsidlyarevich.unity.common.model;

import lombok.Getter;

@Getter
public abstract class AnalysisResult {
    private AnalyzedResource resource = AnalyzedResource.UNKNOWN;
    private Result result;

    public AnalysisResult(Result result) {
        this.result = result;
    }

    public AnalyzedResource getResource() {
        if (result instanceof GitResult) {
            return AnalyzedResource.GITHUB;
        } else {
            return AnalyzedResource.UNKNOWN;
        }
    }
}
