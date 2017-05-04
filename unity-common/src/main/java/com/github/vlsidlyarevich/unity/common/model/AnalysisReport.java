package com.github.vlsidlyarevich.unity.common.model;

import lombok.Getter;

import java.util.Date;

@Getter
public class AnalysisReport {
    private AnalyzedResource resource;
    private Date analyzedAt;
    private Long analysisTime;
    private AnalysisResult result;

    public AnalysisReport(AnalysisResult result, Date analyzedAt, Long analysisTime) {
        this.analyzedAt = analyzedAt;
        this.analysisTime = analysisTime;
        this.result = result;
    }

    public AnalyzedResource getAnalyzedResource() {
        return this.result.getResource();
    }
}
