package com.github.vlsidlyarevich.unity.domain.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class FileSystemFileNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 3194206510987651825L;

    private String key;
    private Object[] args;

    public FileSystemFileNotFoundException(final String message, final String key,
                                           final Object[] args) {
        super(message);
        this.key = key;
        this.args = args;
    }
}
