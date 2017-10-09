package com.github.vlsidlyarevich.unity.domain.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ResourceNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 4750119391296568135L;

    private String message;
}
