package com.github.vlsidlyarevich.unity.domain.model;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

@Data
class DbModel {

    @CreatedDate
    private Date createdAt;

    @LastModifiedDate
    private Date updatedAt;
}
