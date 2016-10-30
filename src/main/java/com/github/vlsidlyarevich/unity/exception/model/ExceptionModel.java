package com.github.vlsidlyarevich.unity.exception.model;

import lombok.Data;

/**
 * Created by vladislav on 10/30/16.
 */
@Data
public class ExceptionModel {

    private String message;

    public ExceptionModel(String message) {
        this.message = message;
    }

}
