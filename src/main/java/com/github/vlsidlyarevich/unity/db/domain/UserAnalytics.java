package com.github.vlsidlyarevich.unity.db.domain;

import com.github.vlsidlyarevich.unity.common.model.AnalysisReport;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@Document(collection = "usersAnalytics")
public class UserAnalytics extends DbModel implements Serializable {

    private static final long serialVersionUID = 1632724154325649381L;

    @Id
    private String id;
    private String userId;
    private List<AnalysisReport> reports;

    public UserAnalytics(final String userId, final List<AnalysisReport> reports) {
        this.userId = userId;
        this.reports = reports;
    }
}
