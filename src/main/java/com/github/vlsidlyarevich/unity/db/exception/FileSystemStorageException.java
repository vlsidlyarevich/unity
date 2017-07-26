package com.github.vlsidlyarevich.unity.db.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class FileSystemStorageException extends RuntimeException {

    private static final long serialVersionUID = 7520480870078081658L;

    private String key;
    private String message;
    private Throwable cause;
    private Object[] args;

    public FileSystemStorageException(final String message, final Throwable cause) {
        this.message = message;
        this.cause = cause;
    }

    public FileSystemStorageException(final String key, final Object[] args) {
        super();
        this.key = key;
        this.args = args;
    }

    public FileSystemStorageException(final String message,
                                      final String key, final Throwable cause) {
        this.message = message;
        this.cause = cause;
        this.key = key;
    }

    public FileSystemStorageException(final String message, final Throwable cause,
                                      final String key, final Object[] args) {
        this.message = message;
        this.cause = cause;
        this.key = key;
        this.args = args;
    }
}

