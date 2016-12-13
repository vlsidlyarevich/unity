package com.github.vlsidlyarevich.unity.web.exception.model;

import lombok.Data;


@Data
public class ExceptionModel {

    private String message;

    public ExceptionModel(String message) {
        this.message = message;
    }

}
