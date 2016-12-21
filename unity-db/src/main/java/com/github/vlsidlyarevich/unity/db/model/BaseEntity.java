package com.github.vlsidlyarevich.unity.db.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;


@Data
@Document
public class BaseEntity implements Serializable {

    @Id
    private String id;
    private String createdAt;
    private String updatedAt;

    public BaseEntity() {
    }
}
