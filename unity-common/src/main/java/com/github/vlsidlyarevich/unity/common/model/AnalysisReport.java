package com.github.vlsidlyarevich.unity.common.model;

import lombok.Getter;

import java.util.Date;

@Getter
public class AnalysisReport {
    private AnalyzedResource resource;
    private Date analyzedAt;
    private Long analysisTime;
    private Result result;

    public AnalysisReport(Result result, Date analyzedAt, Long analysisTime) {
        this.analyzedAt = analyzedAt;
        this.analysisTime = analysisTime;
        this.result = result;
        this.setAnalyzedResource(result);
    }

    private void setAnalyzedResource(Result result) {
        if (result instanceof GitResult) {
            this.resource = AnalyzedResource.GITHUB;
        } else {
            this.resource = AnalyzedResource.UNKNOWN;
        }
    }
}
