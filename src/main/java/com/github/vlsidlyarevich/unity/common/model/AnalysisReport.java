package com.github.vlsidlyarevich.unity.common.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Getter
@EqualsAndHashCode
public class AnalysisReport implements Serializable {

    private static final long serialVersionUID = -373736423253240530L;

    private String id;
    private AnalyzedResource resource;
    private Date analyzedAt;
    private Long analysisTime;
    private AnalysisResult result;

    public AnalysisReport(final AnalysisResult result, final Date analyzedAt,
                          final Long analysisTime) {
        this.id = UUID.randomUUID().toString();
        this.analyzedAt = analyzedAt;
        this.analysisTime = analysisTime;
        this.result = result;
        this.setAnalyzedResource(result);
    }

    private void setAnalyzedResource(final AnalysisResult analyzedResource) {
        if (analyzedResource instanceof GitResult) {
            this.resource = AnalyzedResource.GITHUB;
        } else {
            this.resource = AnalyzedResource.UNKNOWN;
        }
    }
}
