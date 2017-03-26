package com.github.vlsidlyarevich.unity.db.exception;

import lombok.Data;

@Data
public class FileSystemStorageException extends StorageException {

    private static final long serialVersionUID = 7520480870078081658L;

    private String key;
    private Object[] args;

    public FileSystemStorageException(String key, Object[] args) {
        super();
        this.key = key;
        this.args = args;
    }


    public FileSystemStorageException(String message, String key, Throwable cause) {
        super(message, cause);
        this.key = key;
    }

    public FileSystemStorageException(String message, Throwable cause, String key, Object[] args) {
        super(message, cause);
        this.key = key;
        this.args = args;
    }
}
