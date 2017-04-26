package com.github.vlsidlyarevich.unity.db.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

@Data
@Document(collection = "usersAnalytics")
public class UserAnalytics extends DbModel implements Serializable {

    private static final long serialVersionUID = 1632724154325649381L;

    @Id
    private String id;
    private String userId;
    private List<Object> analyzedData;
}