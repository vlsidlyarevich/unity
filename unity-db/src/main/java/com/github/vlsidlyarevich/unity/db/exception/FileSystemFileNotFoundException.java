package com.github.vlsidlyarevich.unity.db.exception;

import lombok.Data;

@Data
public class FileSystemFileNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 3194206510987651825L;

    private String key;
    private Object[] args;

    public FileSystemFileNotFoundException(final String key, final Object[] args) {
        super();
        this.key = key;
        this.args = args;
    }

    public FileSystemFileNotFoundException(final String message, final String key) {
        super(message);
        this.key = key;
    }

    public FileSystemFileNotFoundException(final String message, final String key,
                                           final Object[] args) {
        super(message);
        this.key = key;
        this.args = args;
    }
}
