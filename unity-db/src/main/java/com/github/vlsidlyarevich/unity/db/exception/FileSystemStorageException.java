package com.github.vlsidlyarevich.unity.db.exception;

import lombok.Data;

@Data
public class FileSystemStorageException extends StorageException {

    private static final long serialVersionUID = 7520480870078081658L;

    private String key;
    private Object[] args;

    public FileSystemStorageException(final String key, final Object[] args) {
        super();
        this.key = key;
        this.args = args;
    }


    public FileSystemStorageException(final String message,
                                      final String key, Throwable cause) {
        super(message, cause);
        this.key = key;
    }

    public FileSystemStorageException(final String message, final Throwable cause,
                                      final String key, Object[] args) {
        super(message, cause);
        this.key = key;
        this.args = args;
    }
}
